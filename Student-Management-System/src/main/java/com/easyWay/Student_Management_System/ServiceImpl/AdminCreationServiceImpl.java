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
        user.setPermission("{\"Student\":{\"studentAttendance\":true,\"StudentAttendanceEdit\":true,\"StudentFees\":true,\"StudentAttendenceManagement\":true,\"StudentAttendanceEditSave\":true,\"StudentRegistrationController\":true,\"StudentAttendanceShow\":true},\"finance\":{\"adminFees\":true},\"faculty\":{\"FacultySalaryDetails\":true,\"FacultySalaryController\":true,\"FacultyAttendanceEditSave\":true,\"FacultyAttendanceEdit\":true,\"FacultyAttendanceShow\":true,\"FacultyAttendanceSave\":true,\"FacultyRegistrationForm\":true},\"Notification\":{\"CreateNotification\":true,\"NotificationList\":true,\"HolidayFormController\":true},\"Subject\":{\"SaveSubjectsToClasses\":true}}\n");
        user.setPassword(encoder.encode(dto.getPassword()));
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