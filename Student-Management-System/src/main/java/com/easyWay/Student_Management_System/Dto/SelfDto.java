package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;

import lombok.Data;


@Data
public class SelfDto {
    public String email;
    public String schoolCode;
    private PermissionsDto permission ;
    private FacultyInfo facultyInfo;

}
