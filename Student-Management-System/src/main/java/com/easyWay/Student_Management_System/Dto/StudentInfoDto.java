package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentInfoDto {

    public String name;
    public String address;
    public String city;
    public String state;
    public FamilyDetails familyDetails;
    public String contact;
    public String gender;
    public String dob;
    public String email;
    public String cls;
    public String department;
    public String category;


}
