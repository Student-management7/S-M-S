package com.easyWay.Student_Management_System.Feign;


import com.easyWay.Student_Management_System.Dto.UserDto;
import com.easyWay.Student_Management_System.Security.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "database-service" , url = "http://localhost:8091")
public interface DatabaseService {

    @GetMapping("/user/getByEmail")
    public Users findByMail(@RequestParam String email);

    @PostMapping("/user/save")
    public Users saveUser(@RequestBody UserDto userDto);

    @PostMapping("/user/update")
    public Users updateUser(@RequestBody UserDto userDto);
}