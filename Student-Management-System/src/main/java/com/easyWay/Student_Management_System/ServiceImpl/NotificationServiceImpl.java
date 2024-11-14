package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.NotificationDto;
import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.NotificationRepo;
import com.easyWay.Student_Management_System.Service.NotificationService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
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


    @Override
    public String saveNotification(NotificationDto notificationDto) {

        NotificationEntity entity = new NotificationEntity();
        convertDtoToEntity(notificationDto, entity);
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

        List<NotificationEntity> savedData = repo.findAll();
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


    void convertDtoToEntity(NotificationDto notificationDto, NotificationEntity entity){
        entity.setStartDate(notificationDto.getStartDate());
        entity.setEndDate(notificationDto.getEndDate());
        entity.setDescription(notificationDto.getDescription());
        entity.setCato(notificationDto.getCato().toLowerCase());
        entity.setClassName(gson.toJson(notificationDto.getClassName()));
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
}