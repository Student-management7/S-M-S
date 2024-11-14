package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;

@Data
public class FacultyAttendanceRequestDto {
    String date;
    List<FactListDto> factList;
}
