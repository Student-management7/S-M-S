package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;
import com.easyWay.Student_Management_System.Service.FacultyAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faculty")
public class FacultyAttendanceController {

    @Autowired
    FacultyAttendanceService service;

    @PostMapping("/attendanceSave")
    public String attendanceSave(@RequestBody FacultyAttendanceRequestDto details){
        return service.saveAttendance(details);
    }

    @PostMapping("/attendanceEdit")
    public String attendanceEdit(@RequestBody FacultyAttendanceRequestDto details){
        return service.editAttendance(details);
    }

}
