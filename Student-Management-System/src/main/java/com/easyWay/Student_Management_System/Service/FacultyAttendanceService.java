package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;

public interface FacultyAttendanceService {

    String saveAttendance(FacultyAttendanceRequestDto dto);

    String editAttendance(FacultyAttendanceRequestDto dto);



}
