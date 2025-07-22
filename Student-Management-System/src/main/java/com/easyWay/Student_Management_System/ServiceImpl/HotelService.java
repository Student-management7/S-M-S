package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Entity.AdminCreationEntity;
import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import com.easyWay.Student_Management_System.Entity.HotelCustomerEntity;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.AdminCreationRepo;
import com.easyWay.Student_Management_System.Repo.HotelCreationRepo;
import com.easyWay.Student_Management_System.Repo.HotelCustomerEntityRepo;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

@Service
public class HotelService {

    @Autowired
    HotelCustomerEntityRepo hotelCustomerEntityRepo;

    @Autowired
    AdminCreationRepo infoRepo;

    @Autowired
    HotelCreationRepo hotelCreationRepo;

    @Autowired
    UsersRepo usersRepo;

    private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(11);


    public String saveCustomerDetail(@RequestBody HotelCustomerEntity hotelCustomerEntity){
        hotelCustomerEntityRepo.save(hotelCustomerEntity);
        return "Saved Successfully";
    }

    public ArrayList<HotelCustomerEntity> getUserDetails() {

        return null;
    }

    public String saveAdmin(HotelCreationEntity entity) {


        Users user = new Users();
        user.setEmail(entity.getEmail());
        user.setPassword(encoder.encode(entity.getPassword()));
        user = usersRepo.save(user);
        entity.setUserInfo4(user);
        hotelCreationRepo.save(entity);
        return "Saved successfully";
    }
}
