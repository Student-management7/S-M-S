package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Service.PermissionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    FacultyInfoRepo repo;

    @Autowired
    Gson gson;

    @Override
    public String savePermission(PermissionsDto dto) {
        FacultyInfo facultyData = repo.getById(dto.getFacultyId());
        facultyData.getUserInfo().setPermission(gson.toJson(dto.permissions));
        repo.save(facultyData);
        return "Saved Successfully";
    }
}
