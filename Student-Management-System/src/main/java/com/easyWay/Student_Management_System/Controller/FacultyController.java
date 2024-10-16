package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @PostMapping("/save")
    public String saveFaculty(@RequestBody FacultyInfoDto details){

        return facultyService.saveFaculty(details);

    }
}
