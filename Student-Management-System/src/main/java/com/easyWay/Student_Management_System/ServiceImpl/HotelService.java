package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.HotelCheckInnDto;
import com.easyWay.Student_Management_System.Entity.*;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.*;
import com.easyWay.Student_Management_System.Security.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    public String saveAdmin(HotelCreationEntity entity) {

        if(!checkEmail(entity.getEmail())){
            throw new BadRequestException("Email Already Present");
        }

        Users user = new Users();
        user.setEmail(entity.getEmail());
        user.setPassword(encoder.encode(entity.getPassword()));
        String schoolCode = entity.getEmail().substring(1, 4).toUpperCase() + RANDOM.nextInt(9999);
        user.setSchoolCode(schoolCode);
        user.setPermission("Hotel");
        user.setActive(true);
        user.setRole("USER");
        user = usersRepo.save(user);
        entity.setHotelCode(user.getSchoolCode());
        entity.setUserInfo4(user);
        hotelCreationRepo.save(entity);
        return "Saved successfully";
    }

    private boolean checkEmail(String email) {
        Users users = usersRepo.findUsersByEmail(email);
        return ObjectUtils.isEmpty(users);
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


    public void saveCustomerDetail(String name, String address, String city, String state, String contact, String adharNo,
                                   String nationality, MultipartFile faceImage, MultipartFile adharImgF,
                                   MultipartFile adharImgB, MultipartFile fingerprintData){
    try {
        HotelCustomersEntity customer = new HotelCustomersEntity();
        customer.setName(name);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setState(state);
        customer.setContact(contact);
        customer.setAdharNo(adharNo);
        customer.setNationality(nationality);

        // Convert MultipartFile to byte[]; handle empty files gracefully:
        customer.setFace_image(convertMultipartFileToBytes(faceImage));
        customer.setAdharImgF(convertMultipartFileToBytes(adharImgF));
        customer.setAdharImgB(convertMultipartFileToBytes(adharImgB));
        customer.setFingerprint_data(convertMultipartFileToBytes(fingerprintData));

        // Save to DB
        hotelCustomerEntityRepo.save(customer);

    } catch (IOException e) {
        throw new RuntimeException("Failed to save files", e);
    }
}

    private byte[] convertMultipartFileToBytes(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            return file.getBytes();
        }
        return null;
    }
    @Transactional(readOnly = true)
    public List<HotelCustomersEntity> getUserDetails(String aadhar, UUID id) {

        try {
            List<HotelCustomersEntity> entities = new ArrayList<>();
            List<HotelCustomersEntity> returnEntities = new ArrayList<>();

            if (ObjectUtils.isEmpty(id) && ObjectUtils.isEmpty(aadhar)) {
                entities = hotelCustomerEntityRepo.findAll();
            } else if (ObjectUtils.isEmpty(id)) {
                HotelCustomersEntity optionalCustomer = hotelCustomerEntityRepo.findByAadhar(aadhar);

                if (ObjectUtils.isEmpty(optionalCustomer)) {
                    throw new BadRequestException("No Data found");
                }

                HotelCustomersEntity customer = optionalCustomer;
                entities.add(customer);
            } else if (ObjectUtils.isEmpty(aadhar)) {
                Optional<HotelCustomersEntity> optionalCustomer = hotelCustomerEntityRepo.findById(id);

                if (ObjectUtils.isEmpty(optionalCustomer)) {
                    throw new BadRequestException("No Data found");
                }

                HotelCustomersEntity customer = optionalCustomer.get();
                entities.add(customer);
            }
            for (HotelCustomersEntity dto : entities) {
                dto.setFingerprint_data(encodeBase64(dto.getFingerprint_data()).getBytes());
                dto.setFace_image(encodeBase64(dto.getFace_image()).getBytes());
                dto.setAdharImgF(encodeBase64(dto.getAdharImgF()).getBytes());
                dto.setAdharImgB(encodeBase64(dto.getAdharImgB()).getBytes());
                returnEntities.add(dto);
            }

            return returnEntities;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String encodeBase64(byte[] data) {
        if (data == null || data.length == 0) return null;
        return java.util.Base64.getEncoder().encodeToString(data);
    }


    public void saveHotelCheckinn(HotelCheckInEntity userData) {
        userData.setHotelCode(claimService.getLoggedInUserSchoolCode());
        checkInnRepo.save(userData);
    }


    public List<HotelCheckInnDto> getCheckInnDetails() {
        List<HotelCheckInEntity> entities = checkInnRepo.getCheckinn(claimService.getLoggedInUserSchoolCode());
        List<HotelCheckInnDto> dtos = new ArrayList<>();
        for(HotelCheckInEntity entity : entities){
            HotelCheckInnDto dto = new HotelCheckInnDto();
            convertEntityToDto(entity,dto);
            dtos.add(dto);
        }
        return dtos;
    }

    private void convertEntityToDto(HotelCheckInEntity entity, HotelCheckInnDto dto) {
        if(entity == null || dto == null) {
            return;
        }

        dto.setArrivalDate(entity.getArrivalDate());
        dto.setGuestNames(entity.getGuestNames());
        dto.setAddress(entity.getAddress());
        dto.setContact(entity.getContact());
        dto.setCompany(entity.getCompany());
        dto.setIdDetails(entity.getIdDetails());
        dto.setNationality(entity.getNationality());
        dto.setMaleCount(entity.getMaleCount());
        dto.setFemaleCount(entity.getFemaleCount());
        dto.setChildCount(entity.getChildCount());
        dto.setPurpose(entity.getPurpose());
        dto.setComingFrom(entity.getComingFrom());
        dto.setGoingTo(entity.getGoingTo());
        dto.setDepartureDate(entity.getDepartureDate());
        dto.setTransport(entity.getTransport());
        dto.setDeposit(entity.getDeposit());
        dto.setBillNo(entity.getBillNo());
        dto.setAmount(entity.getAmount());
        dto.setRemarks(entity.getRemarks());
        dto.setCustomersEntity(getUserDetails(null,entity.getCustomerId()).get(0));
    }

}
