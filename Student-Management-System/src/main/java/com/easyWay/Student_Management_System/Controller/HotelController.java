package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Entity.HotelCheckinEntity;
import com.easyWay.Student_Management_System.Entity.HotelCustomerEntity;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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

    @GetMapping("/get")
    public ArrayList<HotelCheckinEntity> getCustomer(){
      return hotelService.getUserDetails();
    }

    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody HotelCheckinEntity userData) {
        hotelService.saveHotelCheckinn(userData);
        return ResponseEntity.ok("User Saved Successfully");
    }
}
