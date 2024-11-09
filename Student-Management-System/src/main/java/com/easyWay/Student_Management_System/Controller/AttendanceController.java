package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Dto.AttendanceResponseDto;
import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import com.easyWay.Student_Management_System.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/attendance")
public class AttendanceController {

   @Autowired
    AttendanceService attendanceService;


    @PostMapping("/save")
    public String saveAttendances(@RequestBody AttendanceRequestDto details) {
         return attendanceService.saveAttendances(details);

    }

    @PostMapping("/getAttendance")
    public List<AttendanceResponseDto> getAttendances(@RequestParam String cls, @RequestParam String fromDate,
                                                      @RequestParam String toDate, @RequestParam String subject) {

        return attendanceService.getAttendances(cls, subject, fromDate, toDate);

    }

}
