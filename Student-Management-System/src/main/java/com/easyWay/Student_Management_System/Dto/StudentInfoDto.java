package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.StudentFeeInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
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
    public String admissionClass;
    public String endDate;
    public int totalFees;
    public int remainingFees;

    public List<StudentFeeInfo> feeInfo;
}
