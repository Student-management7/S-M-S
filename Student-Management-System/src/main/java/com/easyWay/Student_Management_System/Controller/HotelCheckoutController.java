package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.HotelCheckInnDto;
import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import com.easyWay.Student_Management_System.ServiceImpl.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelCheckoutController {

    @Autowired
    HotelService hotelService;

    @PostMapping("/checkout")
    public String updateCheckoutDate(@RequestBody HotelCheckInnDto dto){
        return hotelService.updateCheckout(dto);
    }
}
