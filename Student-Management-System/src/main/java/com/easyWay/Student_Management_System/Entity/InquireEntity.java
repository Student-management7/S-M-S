package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class InquireEntity extends BaseEntity{
    private String schoolName;
    private String location;
    private String adminContactInfo;
    private String inquiryDate;
    private String currentStatus;
    private String notes;
}
