package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.*;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.FacultyService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    Gson gson;

    @Autowired
    FacultyInfoRepo infoRepo;

    @Autowired
    ClaimService claimService;

    @Override
    public String saveFaculty(FacultyInfoDto details) {
        checkFacultyValidations(details);
        FacultyInfo facultyInfo = new FacultyInfo();
        convertDtoToEntity(details, facultyInfo);
        facultyInfo.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        infoRepo.save(facultyInfo);
        return "Saved Successfully";
    }

    @Override
    public String updateFaculty(FacultyInfoDto faculty) {
        try {
            FacultyInfo saveFaculty = infoRepo.getById(claimService.getLoggedInUserSchoolCode(), faculty.getFact_id());
            updateFacultyDetails(saveFaculty, faculty);
            return "Faculty saved successfully";
        } catch (Exception e) {
            throw new BadRequestException("Data not found");
        }
    }

    @Override
    public String deleteFaculty(UUID id) {
        try {
            FacultyInfo entity = infoRepo.getById(id);
            entity.setDelete(true);
            infoRepo.save(entity);
            return "Deleted successfully";

        } catch (Exception e) {
            throw new BadRequestException("Not deleted successfully");
        }
    }

    @Override
    public List<FacultyInfoDto> getAllFaculty(UUID id) {
        try {
            List<FacultyInfoDto> facultyInfoDto = new ArrayList<>();
            if(ObjectUtils.isEmpty(id)) {

                List<FacultyInfo> facultyList = infoRepo.findAllFaculty();
                if (ObjectUtils.isEmpty(facultyList)) {
                    throw new BadRequestException("Data not found");
                }

                for (FacultyInfo facultyinfo : facultyList) {
                    FacultyInfoDto dto = new FacultyInfoDto();
                    convertEntityToDto(facultyinfo, dto);
                    facultyInfoDto.add(dto);
                }
                return facultyInfoDto;
            } else {
                FacultyInfo facultyInfo = infoRepo.getById(id);
                if (ObjectUtils.isEmpty(facultyInfo)) {
                    throw new BadRequestException("Data not found");
                }
                FacultyInfoDto dto = new FacultyInfoDto();
                convertEntityToDto(facultyInfo, dto);
                facultyInfoDto.add(dto);
                return  facultyInfoDto;
            }
        } catch (Exception e) {

            throw new BadRequestException("Data not found");
        }
    }

    void updateFacultyDetails(FacultyInfo saveFaculty, FacultyInfoDto details) {
        saveFaculty.setFact_id(details.getFact_id());
        saveFaculty.setFact_Name(details.getFact_Name());
        saveFaculty.setFact_cls(gson.toJson(details.getFact_Cls()));
        saveFaculty.setFact_state(details.getFact_state());
        saveFaculty.setFact_status(details.getFact_Status());
        saveFaculty.setFact_city(details.getFact_city());
        saveFaculty.setFact_address(details.getFact_address());
        saveFaculty.setFact_gender(details.getFact_gender());
        saveFaculty.setFact_contact(details.getFact_contact());
        saveFaculty.setFact_joiningDate(details.getFact_joiningDate());
        saveFaculty.setFact_leavingDate(details.getFact_leavingDate());

        saveFaculty.setFact_graduation(gson.toJson(details.getFact_qualification()));

        infoRepo.save(saveFaculty);
    }

    private void convertDtoToEntity(FacultyInfoDto dto, FacultyInfo entity) {

        entity.setFact_id(UUID.randomUUID());
        entity.setFact_Name(dto.getFact_Name());
        entity.setFact_email(dto.getFact_email());
        entity.setFact_contact(dto.getFact_contact());
        entity.setFact_gender(dto.getFact_gender());
        entity.setFact_address(dto.getFact_address());
        entity.setFact_city(dto.getFact_city());
        entity.setFact_state(dto.getFact_state());
        entity.setFact_joiningDate(dto.getFact_joiningDate());
        entity.setFact_leavingDate(dto.getFact_leavingDate());
        entity.setFact_graduation(gson.toJson(dto.getFact_qualification()));

        entity.setFact_cls(gson.toJson(dto.getFact_Cls()));
        entity.setFact_status(dto.getFact_Status());

    }

    void checkFacultyValidations(FacultyInfoDto details) {

        if (details == null) {
            throw new BadRequestException("Faculty details can't be null");
        }
        if (StringUtil.isBlank(details.getFact_Name())) {
            throw new BadRequestException("Name can't be empty");
        }

        if (StringUtil.isBlank(details.getFact_email())) {
            throw new BadRequestException("Email can't be  empty");
        }

        if (StringUtil.isBlank(details.getFact_contact())) {
            throw new BadRequestException("Contact can't be empty");
        }

        if (StringUtil.isBlank(details.getFact_address())) {
            throw new BadRequestException("Address can't be empty");
        }

        if (StringUtil.isBlank(details.getFact_city())) {
            throw new BadRequestException("City can't be empty");
        }

        if (StringUtil.isBlank(details.getFact_state())) {
            throw new BadRequestException("State can't be empty");
        }

        if (StringUtil.isBlank(details.getFact_joiningDate())) {
            throw new BadRequestException("Joining date can't be empty");
        }
    }
    private void convertEntityToDto(FacultyInfo entity, FacultyInfoDto dto) {

        dto.setFact_id(entity.getFact_id());
        dto.setFact_Name(entity.getFact_Name());
        dto.setFact_email(entity.getFact_email());
        dto.setFact_contact(entity.getFact_contact());
        dto.setFact_gender(entity.getFact_gender());
        dto.setFact_address(entity.getFact_address());
        dto.setFact_city(entity.getFact_city());
        dto.setFact_state(entity.getFact_state());
        dto.setFact_joiningDate(entity.getFact_joiningDate());
        dto.setFact_leavingDate(entity.getFact_leavingDate());

        Type attendanceListType = new TypeToken<List<FactQualificationDto>>() {}.getType();
        dto.setFact_qualification(gson.fromJson(entity.getFact_graduation() ,attendanceListType));

        Type factClass = new TypeToken<List<FactCls>>() {}.getType();
        dto.setFact_Cls(gson.fromJson(entity.getFact_cls(), factClass));
        dto.setFact_Status(entity.getFact_status());

        dto.setFact_salary(entity.getSalaryInfo());

    }

}
