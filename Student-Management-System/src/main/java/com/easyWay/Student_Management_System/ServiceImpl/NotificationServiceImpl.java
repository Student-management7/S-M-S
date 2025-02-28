package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.NotificationDto;
import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Feign.MailServiceFeignClient;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.NotificationRepo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.NotificationService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepo repo;

    @Autowired
    Gson gson;

    @Autowired
    MailServiceFeignClient mailService;

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @Autowired
    ClaimService claimService;


    @Override
    public String saveNotification(NotificationDto notificationDto) {

        NotificationEntity entity = new NotificationEntity();
        convertDtoToEntity(notificationDto, entity);

        for (String className  : notificationDto.getClassName()){
             List<StudentInfo> data = studentInfoRepo.findByClass(className, claimService.getLoggedInUserSchoolCode());
             if(!data.isEmpty()){
                // sendMail(notificationDto, data);
             }
        }
        repo.save(entity);
        return "Saved successfully";
    }

    @Override
    public String editNotification(NotificationDto notificationDto) {
        NotificationEntity savedData = repo.getById(notificationDto.getId());
        if(ObjectUtils.isEmpty(savedData)){
            throw new BadRequestException("Data not found");
        }
        convertDtoToEntity(notificationDto, savedData);
        repo.save(savedData);
        return "Edited successfully";
    }

    @Override
    public String deleteNotification(UUID id) {
        if (id == null) {
            throw new BadRequestException("Invalid id");
        }

        return repo.findById(id)
                .map(entity -> {
                    repo.delete(entity);
                    return "Deleted successfully.";
                })
                .orElse("No data found for this id = " + id);
    }

    @Override
    public List<NotificationDto> getAllNotification() {

        List<NotificationEntity> savedData = repo.getAll(claimService.getLoggedInUserSchoolCode());

        if (ObjectUtils.isEmpty(savedData)) {
            throw new BadRequestException("No data found");
        }

        List<NotificationDto> result = new ArrayList<>();

        for (NotificationEntity data: savedData){
            NotificationDto dto = new NotificationDto();
            convertEntityToDto(dto, data);
            result.add(dto);
        }

        return result;
    }

    @Override
    public List<NotificationDto> getNotification(String code) {
        List<StudentInfo> studentInfoList = studentInfoRepo.findAll();
        String schoolCode = "";
        for (StudentInfo studentInfo : studentInfoList){
            if(StringUtil.isBlank(studentInfo.getContact()) || StringUtil.isBlank(studentInfo.getName())){
                continue;
            }
            String systemCode = studentInfo.getName().substring(0, 4) + studentInfo.getContact().substring(6, 10);
            if (systemCode.equalsIgnoreCase(code)){
                schoolCode = studentInfo.getSchoolCode();
                break;
            }
        }
        List<NotificationEntity> notifications =  repo.getAll(schoolCode);
        if (ObjectUtils.isEmpty(notifications)){
            throw new BadRequestException("No Data Found for this school");
        }
        List<NotificationDto> dtos = new ArrayList<>();
        for (NotificationEntity entity :notifications){
            NotificationDto dto = new NotificationDto();
            convertEntityToDto(dto, entity);
            dtos.add(dto);
        }
        return dtos;
    }


    void convertDtoToEntity(NotificationDto notificationDto, NotificationEntity entity){
        LocalDateTime startDate = TimeUtils.toStartOfDay(notificationDto.getStartDate());
        LocalDateTime endDate = TimeUtils.toEndOfDay(notificationDto.getEndDate());

        entity.setStartDate(String.valueOf(startDate));
        entity.setEndDate(String.valueOf(endDate));
        entity.setDescription(notificationDto.getDescription());
        entity.setCato(notificationDto.getCato().toLowerCase());
        entity.setClassName(gson.toJson(notificationDto.getClassName()));
        entity.setSchoolCode(claimService.getLoggedInUserSchoolCode());
    }

    void convertEntityToDto(NotificationDto entity, NotificationEntity notificationDto){
        entity.setStartDate(notificationDto.getStartDate());
        entity.setEndDate(notificationDto.getEndDate());
        entity.setDescription(notificationDto.getDescription());
        entity.setCato(notificationDto.getCato());
        entity.setId(notificationDto.getId());
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        entity.setClassName(gson.fromJson(notificationDto.getClassName(), listType));
    }

    void sendMail(NotificationDto notificationDto,  List<StudentInfo> studentInfo){
        for (StudentInfo info :studentInfo){
            mailService.sendEmail(info.getEmail(), "Information", notificationDto.getDescription());
        }
    }
}