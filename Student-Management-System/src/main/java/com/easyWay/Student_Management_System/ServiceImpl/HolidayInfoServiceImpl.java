package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.DateInfoDto;
import com.easyWay.Student_Management_System.Dto.HolidayRequestDto;
import com.easyWay.Student_Management_System.Entity.HolidayInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.HolidayInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.HolidayInfoService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class HolidayInfoServiceImpl implements HolidayInfoService {

    @Autowired
    Gson gson;

    @Autowired
    HolidayInfoRepo holidayInfoRepo;

    @Autowired
    ClaimService claimService;

    @Override
    public String saveHolidayInfo(HolidayRequestDto details) {
        for (DateInfoDto dateDto : details.getDate()) {
            HolidayInfo entity = new HolidayInfo();
            convertDtoToEntity(details, dateDto, entity);
            entity.setSchoolCode(claimService.getLoggedInUserSchoolCode());
            holidayInfoRepo.save(entity);
        }
        return "Saved successfully";
    }

    @Override
    public String editHolidayInfo(HolidayRequestDto details) {

        if (ObjectUtils.isEmpty(details)) {
            throw new BadRequestException("Something went wrong");
        }

        List<String> errorList = new ArrayList<>();

        for (DateInfoDto dateDto : details.getDate()) {
            try {
                HolidayInfo entity = holidayInfoRepo.getById(dateDto.getId());
                convertDtoToEntity(details, dateDto, entity);

                holidayInfoRepo.save(entity);
            } catch (Exception e) {
                errorList.add("No data found for this id = " + dateDto.getId());
            }
        }
        if (errorList.isEmpty()) {
            return "Edited successfully.";
        } else {
            return "Errors: " + errorList;
        }
    }

    @Override
    public String deleteHolidayInfo(UUID id) {
        if (id == null) {
            throw new BadRequestException("Invalid id");
        }

        return holidayInfoRepo.findById(id)
                .map(entity -> {
                    holidayInfoRepo.delete(entity);
                    return "Deleted successfully.";
                })
                .orElse("No data found for this id = " + id);
    }

    @Override
    public List<HolidayRequestDto> getAllHolidayRequestDto() {
        List<HolidayInfo> savedData = holidayInfoRepo.findAll();

        if (ObjectUtils.isEmpty(savedData)) {
            throw new BadRequestException("No data found");
        }

        List<HolidayRequestDto> result = new ArrayList<>();

        for (HolidayInfo data : savedData) {
            HolidayRequestDto dto = new HolidayRequestDto();
            convertEntityToDto(data, dto, new DateInfoDto());
            result.add(dto);
        }
        return result;
    }


    void convertDtoToEntity(HolidayRequestDto details, DateInfoDto dateDto, HolidayInfo entity) {
        LocalDateTime startDate = TimeUtils.toStartOfDay(dateDto.getStartDate());
        LocalDateTime endDate = TimeUtils.toEndOfDay(dateDto.getEndDate());
        entity.setClassName(gson.toJson(details.getClassName()));
        entity.setDescription(dateDto.getDescription());
        entity.setStartDate(startDate);
        entity.setEndDate(endDate);
    }

    void convertEntityToDto(HolidayInfo entity, HolidayRequestDto details, DateInfoDto dateDto) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        details.setClassName(gson.fromJson(entity.getClassName(), listType)); // Assuming className was JSON
        List<DateInfoDto> dates = new ArrayList<>();
        dateDto.setDescription(entity.getDescription());
        dateDto.setStartDate(String.valueOf(entity.getStartDate()));
        dateDto.setEndDate(String.valueOf(entity.getEndDate()));
        dateDto.setId(entity.getId());
        dates.add(dateDto);
        details.setDate(dates);
    }

}
