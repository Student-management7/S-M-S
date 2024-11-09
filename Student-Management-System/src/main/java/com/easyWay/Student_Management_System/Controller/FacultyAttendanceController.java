package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyAttendanceRequestDto;
import com.easyWay.Student_Management_System.Service.FacultyAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAttendance")
    public List<FacultyAttendanceRequestDto> getAttendance(@RequestParam String fromDate,
                                                           @RequestParam String toDate){
      return service.getAttendance(fromDate ,toDate);
    }
}
