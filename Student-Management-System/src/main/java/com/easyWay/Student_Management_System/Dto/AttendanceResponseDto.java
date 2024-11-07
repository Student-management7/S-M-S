package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class AttendanceResponseDto {
    private LocalDateTime date;
    private List<AttendanceDto> students;
}
