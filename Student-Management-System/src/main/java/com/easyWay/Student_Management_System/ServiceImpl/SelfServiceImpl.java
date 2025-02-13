package com.easyWay.Student_Management_System.ServiceImpl;


import com.easyWay.Student_Management_System.Dto.*;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.SelfService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
public class SelfServiceImpl implements SelfService {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    ClaimService claimService;

    @Autowired
    Gson gson;

    @Override
    public SelfDto getSelf() {

        Users users = usersRepo.findUsersByEmail(claimService.getLoggedInUserEmail().get());
        SelfDto selfDto = new SelfDto();
        convertEntityToDto(selfDto, users);
        return selfDto;
    }

    void convertEntityToDto(SelfDto selfDto, Users users) {
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
        }
    }
}
