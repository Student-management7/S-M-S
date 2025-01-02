package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;

import java.util.UUID;

public interface SchoolCreationService {
    String saveSchool(SchoolCreationDto details);
    String deleteSchool(UUID id);

    String updateSchool(SchoolCreationDto details);
}
