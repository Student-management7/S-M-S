package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import com.easyWay.Student_Management_System.Entity.Users;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.SchoolCreationRepo;
import com.easyWay.Student_Management_System.Repo.UsersRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.SchoolCreationService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

@Service
@Slf4j
public class SchoolCreationImpl implements SchoolCreationService {

    @Autowired
    SchoolCreationRepo infoRepo;

    @Autowired
    UsersRepo userRepo;

    @Autowired
    ClaimService claimService;

    @Autowired
    UsersRepo usersRepo;
    private BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(11);

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String saveSchool(SchoolCreationDto details) {
        int len = (details.getPassword().length());
        if (!(len >= 6 && len < 16)){
            throw new BadRequestException("Use password between 6 to 16 character");
        }
        if(!checkRegistration(details)){
            throw new BadRequestException("Email already registered");
        }
        checkSchoolCreationValidation(details);
        SchoolCreationEntity schoolCreationEntity = new SchoolCreationEntity();
        convertDtoToEntity(details, schoolCreationEntity);
        Users users = new Users();
        users.setEmail(details.getEmail());
        users.setPassword(encoder.encode(details.getPassword()));
        users.setPermission("{\"student\":{\"studentAttendance\":false,\"studentAttendanceEdit\":false,\"studentFees\":false,\"studentAttendenceManagement\":false,\"studentAttendanceEditSave\":false,\"studentRegistrationController\":false,\"studentAttendanceShow\":false,\"studentFeesController\":false,\"studentFeesForm\":false,\"studentFeesDetails\":false,\"studentReportForm\":false,\"studentReport\":false,\"studentDetails\":false,\"bulkupload\":false},\"faculty\":{\"facultySalaryDetails\":false,\"facultySalaryController\":false,\"facultyAttendanceEditSave\":false,\"facultyAttendanceEdit\":false,\"facultyAttendanceShow\":false,\"facultyAttendanceSave\":false,\"facultyRegistrationForm\":false,\"facultyDetails\":false},\"finance\":{\"adminFees\":false,\"feesController\":false,\"FeesManagement\":false,\"permission\":false},\"notification\":{\"createNotification\":false,\"notificationList\":false,\"holidayFormController\":false,\"notificationController\":false},\"subject\":{\"saveSubjectsToClasses\":false,\"classSubjectShow\":false},\"syllabus\":{\"syllabusList\":false,\"uploadSyllabus\":false,\"editSyllabus\":false},\"tc\":{\"transferCertificate\":false,\"marksheet\":false}}");
        String schoolCode = details.getEmail().substring(1,4).toUpperCase()+RANDOM.nextInt(9999);
        users.setSchoolCode(schoolCode);
        users = usersRepo.save(users);
        schoolCreationEntity.setUserInfo2(users);
        schoolCreationEntity.setSchoolCode(schoolCode);
        infoRepo.save(schoolCreationEntity);
        return "Saved Successfully";
    }

    void checkSchoolCreationValidation(SchoolCreationDto details ){
        if (details == null){
            throw new BadRequestException("School creation details can't be null");
        }
        if (StringUtil.isBlank(details.getSchoolName())) {
            throw new BadRequestException("School name can't be empty");
        }
        if (StringUtil.isBlank(details.getSchoolAddress())){
            throw new BadRequestException("School address can't be empty");
        }
        if (StringUtil.isBlank(details.getAdminContact())){
            throw new BadRequestException("Admin contact can't be empty");
        }
        if (StringUtil.isBlank(details.getServiceStartDate().toString())){
            throw new BadRequestException("Service start date can't be empty");
        }
        if (StringUtil.isBlank(details.getEmail())){
            throw new BadRequestException("Email can't be empty");
        }
        if (StringUtil.isBlank(details.getPassword())){
            throw new BadRequestException("PassWord can't be empty");
        }
    }

    @Override
    public String deleteSchool(UUID id) {

        SchoolCreationEntity entity = infoRepo.getById(id);
        if (!ObjectUtils.isEmpty(entity)) {
            List<Users> schoolCreationList = new ArrayList<>();
            List<Users> schoolCreationEntityList = userRepo.getAllDetail(entity.getSchoolCode());
            for (Users creationEntity:schoolCreationEntityList){
                creationEntity.setActive(false);
               schoolCreationList.add(creationEntity);
            }
            userRepo.saveAll(schoolCreationList);
            return "Delete School Successfully";

        } else {
            return "School with ID " + id + " not found.";
        }
    }



    @Override
    public String updateSchool(SchoolCreationDto details) {
        try {
            SchoolCreationEntity saveSchool = infoRepo.getById( details.getId());
            updateSchoolDetails(saveSchool , details);
            return "School edited successfully";

        }catch (Exception e){
            throw new BadRequestException("Data not found");
        }
    }

    @Override
    public  List<SchoolCreationDto> getSchoolDetails(UUID id) {
        List<SchoolCreationEntity> entities = new ArrayList<>();
        if (ObjectUtils.isEmpty(id)) {
          entities  = infoRepo.findAll();
        }else {
            SchoolCreationEntity entity = infoRepo.getById(id);
            entities.add(entity);
        }
        if (ObjectUtils.isEmpty(entities)){
            throw new BadRequestException("No data found");
        }

        List<SchoolCreationDto> dtos = new ArrayList<>();
        for (SchoolCreationEntity entity :entities){
            SchoolCreationDto dto = new SchoolCreationDto();
            dto.setSchoolName(entity.getSchoolName());
            dto.setSchoolAddress(entity.getSchoolAddress());
            dto.setEmail(entity.getUserInfo2().getEmail());
            dto.setId(entity.getId());
            dto.setAdminContact(entity.getAdminContact());
            dto.setSchoolCode(entity.getSchoolCode());
            dto.setCurrentPlan(entity.getCurrentPlan());
//            dto.setRenewalDate(entity.getRenewalDate().toString());
            dto.setRenewalDate(ObjectUtils.isEmpty(entity.getRenewalDate())? " ":entity.getRenewalDate().toString());

            dto.setServiceStartDate(ObjectUtils.isEmpty(entity.getServiceStartDate())? " ":entity.getServiceStartDate().toString());
            dto.setStatus(entity.getStatus());

            dto.setCity(entity.getCity());
            dto.setState(entity.getState());
            dto.setSchoolLandlineNo(entity.getSchoolLandlineNo());
            dto.setOwnerName(entity.getOwnerName());
            dto.setGst(entity.getGst());
            dto.setBoardType(entity.getBoardType());
            dto.setSubscriptionType(entity.getSubscriptionType());
            dto.setRoll(entity.getRoll());
            dtos.add(dto);

        }
        return dtos;
    }


    public void convertDtoToEntity(SchoolCreationDto dto , SchoolCreationEntity entity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dto.getServiceStartDate(), formatter);
        LocalDate end = LocalDate.parse(dto.getRenewalDate(), formatter);
        LocalDateTime serviceStart = localDate.atStartOfDay();
        LocalDateTime endDate = end.atStartOfDay();

        entity.setSchoolName(dto.getSchoolName());
        entity.setSchoolAddress(dto.getSchoolAddress());
        entity.setCurrentPlan(dto.getCurrentPlan());
        entity.setAdminContact(dto.getAdminContact());
        entity.setServiceStartDate(serviceStart);
        entity.setRenewalDate(endDate);

        entity.setStatus(dto.getStatus());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setSchoolLandlineNo(dto.getSchoolLandlineNo());
        entity.setOwnerName(dto.getOwnerName());
        entity.setGst(dto.getGst());
        entity.setBoardType(dto.getBoardType());
        entity.setSubscriptionType(dto.getSubscriptionType());


    }



    public void updateSchoolDetails(SchoolCreationEntity saveSchool , SchoolCreationDto details){
        saveSchool.setSchoolName(details.getSchoolName());
        saveSchool.setSchoolAddress(details.getSchoolAddress());
        saveSchool.setAdminContact(details.getAdminContact());
        saveSchool.setCurrentPlan(details.getCurrentPlan());
        saveSchool.setStatus(details.getStatus());

        saveSchool.setSchoolLandlineNo(details.getSchoolLandlineNo());
        saveSchool.setCity(details.getCity());
        saveSchool.setState(details.getState());
        saveSchool.setOwnerName(details.getOwnerName());
        saveSchool.setGst(details.getGst());
        saveSchool.setBoardType(details.getBoardType());
        saveSchool.setSubscriptionType(details.getSubscriptionType());
        saveSchool.setRoll(details.getRoll());

        infoRepo.save(saveSchool);
    }

    boolean checkRegistration(SchoolCreationDto dto ){
        Users users =  usersRepo.findUsersByEmail(dto.getEmail());
        if (ObjectUtils.isEmpty(users)){
            return true;
        }else {
            return false;
        }
    }

}
