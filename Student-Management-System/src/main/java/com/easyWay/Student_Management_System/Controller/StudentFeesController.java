package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;
import com.easyWay.Student_Management_System.Service.StudentFeesService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentFeesController {

    @Autowired
    private StudentFeesService studentFeesService;

    @PostMapping("/saveFees")
    public String saveFees(@RequestBody StudentFeesDto studentFeesDto) {
        studentFeesService.saveStudentFees(studentFeesDto);
        return "Fees saved successfully";
    }

}
