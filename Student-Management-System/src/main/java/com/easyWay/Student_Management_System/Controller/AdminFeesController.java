package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AdminFeesDto;
import com.easyWay.Student_Management_System.Service.AdminFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin")
public class AdminFeesController {

    @Autowired
    AdminFeesService service;

    @PostMapping("/save")
    public String saveFees(@RequestBody AdminFeesDto details){
        return service.saveFees(details);
    }

    @PostMapping("/delete")
    public String deleteFees(@RequestParam UUID id){
        return service.deleteFees(id);

    }

    @PostMapping("/edit")
    public String editFees(@RequestBody AdminFeesDto id){
        return service.editFees(id);

    }

    @GetMapping("/getAll")
    public List<AdminFeesDto> getFees(){
        return service.getFees();
    }

}
