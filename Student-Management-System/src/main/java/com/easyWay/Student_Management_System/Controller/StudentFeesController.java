package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;
import com.easyWay.Student_Management_System.Service.StudentFeesService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PostMapping("/deleteFees")
    public String deleteFees(@RequestParam UUID id){
       return studentFeesService.deleteFees(id);

    }
    @PostMapping("/editFees")
    public String editFees(@RequestBody StudentFeesDto dto){
        return studentFeesService.editFees(dto);
    }

}
