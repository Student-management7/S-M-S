package com.easyWaySolution.User_Management.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/test")
    public String hello(){
        System.out.println("hello");
        return "hello Brother !";
    }
}
