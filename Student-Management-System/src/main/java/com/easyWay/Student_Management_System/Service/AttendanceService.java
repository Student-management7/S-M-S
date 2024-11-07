package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Dto.AttendanceResponseDto;

import java.util.List;

public interface AttendanceService {
    String saveAttendances(AttendanceRequestDto details);

    List<AttendanceResponseDto> getAttendances(String cls, String subject, String fromDate, String toDate);
}
