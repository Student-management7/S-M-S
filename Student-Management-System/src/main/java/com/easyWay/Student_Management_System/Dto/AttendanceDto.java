package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AttendanceDto {

    public UUID stdId;
    public String attendance;
    public String name;
    public String remark;
}
