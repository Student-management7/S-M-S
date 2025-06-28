package com.easyWay.Student_Management_System.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferCertificateDTO {
    private String tcNo;
    private String admissionNo;
    private String studentName;
    private String fatherName;
    private String motherName;
    private String caste;
    private String dobFigures;
    private String dobWords;
    private String nationality;
    private String lastClass;
    private String promotedTo;
    private String admissionDate;
    private String leavingDate;
    private String reason;
    private String conduct;
    private String remarks;
    private String date;
    private String principalName;
}
