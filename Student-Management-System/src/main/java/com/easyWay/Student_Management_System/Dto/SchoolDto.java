package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SchoolDto {

    private String email;
    private String password;
    private String schoolName;
    private String registrationNumber;
    private String gstNumber;
    private int establishmentYear;
    private String schoolAddress;
    private String city;
    private String state;
    private String pincode;
    private String contactNumber;
    private String affiliationBoard;
    private String panNumber;
    private String mediumOfInstruction;
    private String schoolType;

}
