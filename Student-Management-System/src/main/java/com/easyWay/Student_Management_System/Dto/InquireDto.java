package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class InquireDto {
    public UUID id;
    public String schoolName;
    public String location;
    public String adminContactInfo;
    public String inquiryDate;
    public String currentStatus;
    public String notes;
}
