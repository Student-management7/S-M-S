package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.SelfDto;
import com.easyWay.Student_Management_System.Service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelfController {

    @Autowired
    SelfService service;

    @GetMapping("/self")
    public SelfDto getSelf() {
       return service.getSelf();
    }

}
