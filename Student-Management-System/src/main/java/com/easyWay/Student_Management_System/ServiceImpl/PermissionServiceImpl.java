package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Dto.SelfDto;
import com.easyWay.Student_Management_System.Dto.permissions;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.PermissionService;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    FacultyInfoRepo repo;

    @Autowired
    Gson gson;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ClaimService claimService;



    @Override
    @Transactional
    public String savePermission(PermissionsDto dto) {

        Optional<FacultyInfo> facultyDataOpt = repo.findById(dto.getFacultyId());

        if (facultyDataOpt.isEmpty()) {
            throw new BadRequestException("Faculty with ID " + dto.getFacultyId() + " not found.");
        }

        FacultyInfo facultyData = facultyDataOpt.get();
        facultyData.getUserInfo().setPermission(gson.toJson(dto.permissions));
        repo.save(facultyData);
        return "Saved Successfully";
    }

    @Override
    public List<SelfDto> getAllPermission() {
      List<Users> users =  usersRepo.getAllDetail(claimService.getLoggedInUserSchoolCode());
      List<SelfDto> dtos = new ArrayList<>();
      for (Users user : users){
          if (!ObjectUtils.isEmpty(user.getFacultyInfo())) {
              if (user.getFacultyInfo().isDelete()){
                  continue;
              }
          }
          try {
              SelfDto dto = convertEntityToDto(user);
              dtos.add(dto);
          }catch (Exception e){
            log.info(e.getMessage());
          }

      }
      return dtos;
    }

    SelfDto convertEntityToDto(Users users) {
        SelfDto selfDto = new SelfDto();
        PermissionsDto permissionsDto = new PermissionsDto();

        permissions perms = gson.fromJson(users.getPermission(), permissions.class);

        permissionsDto.setPermissions(perms);
        selfDto.setAdminCreationEntity(users.getAdminCreationEntity());
        selfDto.setSchoolCreationEntity(users.getSchoolCreationEntity());
        selfDto.setFacultyInfo(users.getFacultyInfo());
        selfDto.setSchoolCode(users.getSchoolCode());
        selfDto.setEmail(users.getEmail());
        selfDto.setPermission(permissionsDto);
        if(!ObjectUtils.isEmpty(users.getSchoolCreationEntity())){
            selfDto.setRole("user");
        } else if (!ObjectUtils.isEmpty(users.getAdminCreationEntity())) {
            selfDto.setRole("admin");
        } else if (!ObjectUtils.isEmpty(users.getFacultyInfo())) {
            selfDto.setRole("sub-user");
            selfDto.getPermission().setFacultyId(users.getFacultyInfo().getId());
        }
        return selfDto;
    }
}
