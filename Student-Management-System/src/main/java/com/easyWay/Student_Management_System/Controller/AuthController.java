package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.UsersDto;
import com.easyWay.Student_Management_System.Security.JWTService;
import com.easyWay.Student_Management_System.Service.UserService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;


    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody UsersDto userDto) {
         return ResponseEntity.ok(userService.registerUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UsersDto userDto) throws BadRequestException {

        String token = userService.loginUser(userDto);
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("token", token);
        return ResponseEntity.ok(responseBody);

    }
    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody UsersDto userDto){
        return ResponseEntity.ok(userService.forgetUserPassword(userDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        jwtService.blacklistToken(token);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logged out successfully");
    }
}