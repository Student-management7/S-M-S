package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Dto.HotelEntityDto;
import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import com.easyWay.Student_Management_System.Service.AdminCreationService;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotelCreation")
public class HotelCreationController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/save")
    public String saveAdmin(@RequestBody HotelCreationEntity dto){
        return hotelService.saveAdmin(dto);
    }

    @GetMapping("/get")
    public List<HotelEntityDto> getDetails(@RequestParam(required = false) UUID id){
        return hotelService.getDetails(id);
    }
}
