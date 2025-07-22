package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Entity.AdminCreationEntity;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.AdminCreationRepo;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Service.AdminCreationService;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCreationServiceImpl implements AdminCreationService {

   @Autowired
   AdminCreationRepo infoRepo;

   @Autowired
   Gson gson;

   @Autowired
   UsersRepo usersRepo;

    private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(11);

    @Override
    public String saveAdmin(AdminCreationDto dto) {
        AdminCreationEntity entity = new AdminCreationEntity();
        if(!checkEmail(dto)){
            throw new BadRequestException("Email Already Present");
        }
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());


        entity.setAssignedSchools(gson.toJson(dto.getAssignedSchools()));
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setPermission("{\"student\":{\"studentAttendance\":true,\"studentAttendanceEdit\":true,\"studentAttendenceManagement\":true,\"studentFees\":true,\"studentAttendanceEditSave\":true,\"studentRegistrationController\":true,\"studentAttendanceShow\":true,\"studentFeesController\":true,\"studentFeesForm\":true,\"studentFeesDetails\":true,\"studentReportForm\":true,\"studentReport\":true,\"studentDetails\":true},\"faculty\":{\"facultySalaryDetails\":true,\"facultySalaryController\":true,\"facultyAttendanceEditSave\":true,\"facultyAttendanceEdit\":true,\"facultyAttendanceShow\":true,\"facultyAttendanceSave\":true,\"facultyRegistrationForm\":true,\"facultyDetails\":true},\"finance\":{\"adminFees\":true,\"feesController\":true,\"permission\":true},\"notification\":{\"createNotification\":true,\"notificationList\":true,\"holidayFormController\":true,\"notificationController\":true},\"subject\":{\"saveSubjectsToClasses\":true,\"classSubjectShow\":true}}");
        user = usersRepo.save(user);
        entity.setUserInfo3(user);
        infoRepo.save(entity);
        return "Saved successfully";

    }

    @Override
    public List<AdminCreationDto> getAdminDetails() {
        List<Users> details = usersRepo.getAdminDetail();
        List<AdminCreationDto> dtos = new ArrayList<>();
        for (Users user:details){
            AdminCreationDto dto = new AdminCreationDto();
            dto.setName(user.getAdminCreationEntity().getName());
            dto.setEmail(user.getEmail());
            dto.setRole(user.getAdminCreationEntity().getRole());
            dto.setId(user.getId());
            Type listType = new TypeToken<ArrayList<String>>() {}.getType();
            dto.setAssignedSchools(gson.fromJson(user.getAdminCreationEntity().getAssignedSchools(), listType));
            dtos.add(dto);
        }
        return dtos;
    }
    boolean checkEmail(AdminCreationDto dto){
        Users users = usersRepo.findUsersByEmail(dto.getEmail());
        return ObjectUtils.isEmpty(users);
    }
}