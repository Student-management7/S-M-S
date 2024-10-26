package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyService facultyService;

    @Autowired
    FacultyInfoRepo infoRepo;

    @PostMapping("/save")
    public String saveFaculty(@RequestBody FacultyInfoDto details) {

        return facultyService.saveFaculty(details);

    }

    @PostMapping("/Update")
    public String updateFactulty(@RequestBody FacultyInfoDto faculty) {
        return facultyService.updateFaculty(faculty);
    }

    @GetMapping("/all")
    public List<FacultyInfo>a(){
        return infoRepo.findAll();
    }
}

