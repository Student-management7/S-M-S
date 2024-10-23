package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class StudentInfoDto {
    public UUID id;
    public LocalDateTime creationDateTime;
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
