package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class ReceiptDTO {
    private String receiptNo;
    private String date;
    private String studentName;
    private String studentClass;
    private String rollNo;
    private String section;
    private String fatherName;
    private int tuitionFee;
    private int libraryFee;
    private int sportsFee;
    private String paymentMode;
    private String amountInWords;
}
