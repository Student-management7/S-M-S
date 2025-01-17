package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Service.AdminCreationService;
import com.easyWay.Student_Management_System.Service.AdminFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adminCreation")
public class AdminCreationController {
    @Autowired
    AdminCreationService adminCreationService;

    @PostMapping("/save")
    public String saveAdmin(@RequestBody AdminCreationDto dto){
        return adminCreationService.saveAdmin(dto);
    }

    @GetMapping("/get")
    public List<AdminCreationDto> getAdminDetails(){
        return adminCreationService.getAdminDetails();
    }
}
