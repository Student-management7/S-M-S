package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.UsersDto;
import com.easyWay.Student_Management_System.Service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UsersDto userDto) {
         return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDto userDto) throws BadRequestException {

        return ResponseEntity.ok(userService.loginUser(userDto));
    }
    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody UsersDto userDto){
        return ResponseEntity.ok(userService.forgetUserPassword(userDto));
    }

    @GetMapping("/test")
    public String testController(){
        return "Hello world "+ SecurityContextHolder.getContext().getAuthentication().getAuthorities();
    }

}
