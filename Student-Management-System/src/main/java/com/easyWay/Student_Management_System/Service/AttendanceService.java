package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;

public interface AttendanceService {
    String saveAttendances(AttendanceRequestDto details);
}
