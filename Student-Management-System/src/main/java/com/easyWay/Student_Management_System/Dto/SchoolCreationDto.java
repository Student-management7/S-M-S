package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.BaseEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class SchoolCreationDto extends BaseEntity {
    public String schoolName;
    public String schoolAddress;
    public String adminContact;
    public String serviceStartDate;
    public String currentPlan;
    public String email;
    public String password;
    public String renewalDate;
    public String status;

    public String city;
    public String state;
    public String schoolLandlineNo;
    public String ownerName;
    public String gst;
    public String boardType;
    public String subscriptionType;
    public String roll;

}
