package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;

import java.util.List;

public interface FacultyAttendanceService {

    String saveAttendance(FacultyAttendanceRequestDto dto);

    String editAttendance(FacultyAttendanceRequestDto dto);

    List<FacultyAttendanceRequestDto> getAttendance(String from ,String to);



}
