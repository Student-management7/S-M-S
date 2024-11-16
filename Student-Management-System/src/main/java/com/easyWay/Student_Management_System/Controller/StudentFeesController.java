package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;
import jakarta.persistence.Column;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentFeesController {

    @PostMapping("/saveFees")
    public String saveFees(StudentFeesDto studentFeesDto) {

        return "Fees saved successfully";
    }

}
