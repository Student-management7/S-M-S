package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FacultyAttendanceRequestDto {
    String date;
    UUID id;
    List<FactListDto> factList;
}
