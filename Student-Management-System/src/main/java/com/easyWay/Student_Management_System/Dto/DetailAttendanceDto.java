package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class DetailAttendanceDto {

    public int totalDays;
    public int presentDays;
    public int absentDays;
    public float attendancePercentage;


}
