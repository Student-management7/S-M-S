package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AdminFeesDto {
    public UUID id;
    public String className;
    public float schoolFee;
    public float sportsFee;
    public float bookFee;
    public float transportation;
    public List<OtherFeesDto> otherAmount;
    public float totalFee;
}
