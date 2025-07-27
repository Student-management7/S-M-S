package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Entity.HotelCheckInEntity;
import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import com.easyWay.Student_Management_System.Entity.HotelCustomersEntity;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotel/customer")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestParam String name, @RequestParam String address, @RequestParam String city,
                                               @RequestParam String state, @RequestParam String contact, @RequestParam String adharNo, @RequestParam String nationality,
                                               @RequestParam("face_image") MultipartFile face_image, @RequestParam("adharImgF") MultipartFile adharImgF, @RequestParam("adharImgB") MultipartFile adharImgB
            , @RequestParam("fingerprint_data") MultipartFile fingerprint_data) {
        hotelService.saveCustomerDetail(name, address, city, state, contact, adharNo, nationality, face_image, adharImgF, adharImgB, fingerprint_data);
        return ResponseEntity.ok("User registered");
    }

    @GetMapping("/get")
    public List<HotelCustomersEntity> getCustomer(@RequestParam(required = false) UUID id){
      return hotelService.getUserDetails(id);
    }

}
