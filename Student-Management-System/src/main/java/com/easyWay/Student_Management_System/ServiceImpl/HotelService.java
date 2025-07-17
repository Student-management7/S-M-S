package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Entity.HotelCustomerEntity;
import com.easyWay.Student_Management_System.Repo.HotelCustomerEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class HotelService {

    @Autowired
    HotelCustomerEntityRepo hotelCustomerEntityRepo;
    public String saveCustomerDetail(@RequestBody HotelCustomerEntity hotelCustomerEntity){
        hotelCustomerEntityRepo.save(hotelCustomerEntity);
        return "Saved Successfully";
    }
}
