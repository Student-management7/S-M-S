package com.easyWay.Student_Management_System.ServiceImpl;


import com.easyWay.Student_Management_System.Dto.*;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.SelfService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    void convertEntityToDto(SelfDto selfDto, Users users){
        PermissionsDto permissionsDto = new PermissionsDto();
        permissions perms = new permissions();
        perms.setFaculty(gson.fromJson(users.getPermission(), FacultyPersmissionsDto.class));
        perms.setStudent(gson.fromJson(users.getPermission(), StudentPermissionsDto.class));
        perms.setFinance(gson.fromJson(users.getPermission(), FinancePermissionsDto.class));
        permissionsDto.setPermissions(perms);
        selfDto.setPermission(permissionsDto);
        selfDto.setEmail(users.getEmail());
        selfDto.setFacultyInfo(users.getFacultyInfo());
        selfDto.setSchoolCode(users.getSchoolCode());
    }
}
