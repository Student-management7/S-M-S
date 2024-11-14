package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DateInfoDto {
    public UUID id;
    public String startDate;
    public String endDate;
    public String description;

}
