package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;
import com.easyWay.Student_Management_System.Service.SchoolCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/school")
public class SchoolCreationController {
    @Autowired
    SchoolCreationService schoolCreationService;

    @PostMapping("/save")
    public String saveSchool(@RequestBody SchoolCreationDto details){
        return schoolCreationService.saveSchool(details);
    }

    @PostMapping("/delete")
    public String deleteSchool(@RequestParam UUID id){
        return schoolCreationService.deleteSchool(id);
    }

    @PostMapping("/update")
    public String updateSchool(@RequestBody SchoolCreationDto school){
       return schoolCreationService.updateSchool(school);
    }

}
