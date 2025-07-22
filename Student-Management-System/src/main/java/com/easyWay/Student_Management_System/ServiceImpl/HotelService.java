package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.AdminCreationDto;
import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;
import com.easyWay.Student_Management_System.Entity.*;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.*;
import com.easyWay.Student_Management_System.Security.ClaimService;
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
    ClaimService claimService;

    @Autowired
    HotelCreationRepo hotelCreationRepo;

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    HotelCheckInnRepo checkInnRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

    private static final SecureRandom RANDOM = new SecureRandom();


    public String saveCustomerDetail(@RequestBody HotelCustomerEntity hotelCustomerEntity) {
        hotelCustomerEntityRepo.save(hotelCustomerEntity);
        return "Saved Successfully";
    }

    public ArrayList<HotelCheckinEntity> getUserDetails() {

        return checkInnRepo.getCheckinn(claimService.getLoggedInUserSchoolCode());

    }

    public String saveAdmin(HotelCreationEntity entity) {

        if (ObjectUtils.isEmpty(entity.getEmail())) {
            throw new BadRequestException("Email is mandatory");
        }

        Users user = new Users();
        user.setEmail(entity.getEmail());
        user.setPassword(encoder.encode(entity.getPassword()));
        user = usersRepo.save(user);
        String schoolCode = entity.getEmail().substring(1, 4).toUpperCase() + RANDOM.nextInt(9999);
        user.setSchoolCode(schoolCode);
        entity.setHotelCode(user.getSchoolCode());
        entity.setUserInfo4(user);
        hotelCreationRepo.save(entity);
        return "Saved successfully";
    }


    public List<HotelCreationEntity> getDetails(UUID id) {

        List<HotelCreationEntity> entities = new ArrayList<>();

        if (ObjectUtils.isEmpty(id)) {
            List<HotelCreationEntity> savedData =  hotelCreationRepo.findAll();
            return savedData;

        } else {
           HotelCreationEntity saveData = hotelCreationRepo.getById(id);
           entities.add(saveData);
           return entities;

        }
    }

    public void saveHotelCheckinn(HotelCheckinEntity userData) {
        userData.setHotelCode(claimService.getLoggedInUserSchoolCode());
        checkInnRepo.save(userData);
    }


}
