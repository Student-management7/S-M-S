package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;

import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import lombok.Data;


@Data
public class SelfDto {
    private String email;
    private String schoolCode;
    private PermissionsDto permission ;
    private FacultyInfo facultyInfo;
    private SchoolCreationEntity schoolCreation;
}
