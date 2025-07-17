package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Entity.HotelCustomerEntity;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel/customer")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody HotelCustomerEntity userData) {
        hotelService.saveCustomerDetail(userData);
        return ResponseEntity.ok("User registered");
    }

}
