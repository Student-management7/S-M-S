package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AdminFeesDto {
    public UUID id;
    public String className;
    public int schoolFee;
    public int sportsFee;
    public int bookFee;
    public int transportation;
    public List<OtherFeesDto> otherAmount;
    public int totalFee;
}
