package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.HotelCheckInnDto;
import com.easyWay.Student_Management_System.Entity.HotelCheckInEntity;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotelCheckInn")
public class HotelCheckInnController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/save")
    public ResponseEntity<String> registerUser(@RequestBody HotelCheckInEntity userData) {
        hotelService.saveHotelCheckinn(userData);
        return ResponseEntity.ok("User Saved Successfully");
    }

    @GetMapping("/get")
    public List<HotelCheckInnDto> getDetails(){
        return hotelService.getCheckInnDetails();
    }
}
