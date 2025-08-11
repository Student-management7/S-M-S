package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.AdminCreationEntity;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;

import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import lombok.Data;


@Data
public class SelfDto {
    public String email;
    public String schoolCode;
    private PermissionsDto permission ;
    private FacultyInfo facultyInfo;
    private SchoolCreationEntity schoolCreationEntity;
    private AdminCreationEntity adminCreationEntity;
    private String role;
    private HotelCreationEntity hotelCreationEntity;
}
