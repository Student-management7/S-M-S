package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import com.easyWay.Student_Management_System.Service.FacultySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/faculty")
public class FacultySalaryController {
    @Autowired
    FacultySalaryService facultyAttendanceService;
    @PostMapping("/salary/save")
    public String saveSalary(@RequestBody FacultySalaryDto details){
        return facultyAttendanceService.saveFacultySalary(details);
    }

}
