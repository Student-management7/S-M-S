package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AttendanceRequestDto;
import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import com.easyWay.Student_Management_System.Service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/attendance")
public class AttendanceController {

   @Autowired
    AttendanceService attendanceService;


    @PostMapping("/save")
    public String saveAttendances(@RequestBody AttendanceRequestDto details) {
         return attendanceService.saveAttendances(details);

    }

}
