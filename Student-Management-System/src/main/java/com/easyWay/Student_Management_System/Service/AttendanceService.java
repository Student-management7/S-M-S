package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Dto.AttendanceResponseDto;
import com.easyWay.Student_Management_System.Dto.DetailAttendanceDto;

import java.util.List;
import java.util.UUID;

public interface AttendanceService {
    String saveAttendances(AttendanceRequestDto details,  boolean masterAttendance);

    List<AttendanceResponseDto> getAttendances(String cls, String subject, String fromDate, String toDate, boolean masterAttendance);

    String attendanceUpdate(AttendanceRequestDto details ,boolean masterAttendance);

    DetailAttendanceDto detailAttendance(UUID id, String startDate, String endDate, String cls, String subject);
}
