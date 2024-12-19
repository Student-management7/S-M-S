package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.PermissionsDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Service.PermissionService;
import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    FacultyInfoRepo repo;

    @Autowired
    Gson gson;

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
}
