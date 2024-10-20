package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.ClassResponseDto;
import com.easyWay.Student_Management_System.Service.ClassResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/class")
public class ClassController {

    @Autowired
    ClassResponseService classResponseService;


    @GetMapping("/data")
    public ClassResponseDto getClassResponse(){
       return classResponseService.getData();
    }

}
