package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private static final SecureRandom RANDOM = new SecureRandom();


    public String saveCustomerDetail(@RequestBody HotelCustomerEntity hotelCustomerEntity){
        hotelCustomerEntityRepo.save(hotelCustomerEntity);
        return "Saved Successfully";
    }

    public ArrayList<HotelCustomerEntity> getUserDetails() {

        return null;
    }

    public String saveAdmin(HotelCreationEntity entity) {

        if (ObjectUtils.isEmpty(entity.getEmail())){
            throw new BadRequestException("Email is mandatory");
        }

        Users user = new Users();
        user.setEmail(entity.getEmail());
        user.setPassword(encoder.encode(entity.getPassword()));
        user = usersRepo.save(user);
        String schoolCode = entity.getEmail().substring(1,4).toUpperCase()+RANDOM.nextInt(9999);
        user.setSchoolCode(schoolCode);
        entity.setUserInfo4(user);
        hotelCreationRepo.save(entity);
        return "Saved successfully";
    }


    public List<HotelCreationEntity> getDetails(UUID id) {

        if(ObjectUtils.isEmpty(id)){
            hotelCreationRepo.findAll();
        }
        return null;
    }
}
