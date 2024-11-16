package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
@Data
public class AdminFeesDto {
    public String className;
    public int schoolFee;
    public int sportsFee;
    public int bookFee;
    public int transportation;
    public List<OtherFeesDto> otherAmount;
}
