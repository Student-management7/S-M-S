package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import com.easyWay.Student_Management_System.Service.FacultySalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController

@RequestMapping("/faculty")
public class FacultySalaryController {
    @Autowired
    FacultySalaryService facultySalaryService;
    @PostMapping("/salary/save")
    public String saveSalary(@RequestBody FacultySalaryDto details){
        return facultySalaryService.saveFacultySalary(details);
    }

    @PostMapping("/salary/delete")
    public String deleteSalary(@RequestParam UUID id){
        return facultySalaryService.deleteSalary(id);
    }

    @PostMapping("/salary/edit")
    public String editSalary(@RequestBody FacultySalaryDto dto){
        return facultySalaryService.editSalary(dto);
    }

}
