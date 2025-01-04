package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.SchoolCreationDto;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.SchoolCreationRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.SchoolCreationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class SchoolCreationImpl implements SchoolCreationService {

    @Autowired
    SchoolCreationRepo infoRepo;

    @Autowired
    ClaimService claimService;

    @Override
    public String saveSchool(SchoolCreationDto details) {
        SchoolCreationEntity schoolCreationEntity = new SchoolCreationEntity();
        convertDtoToEntity(details, schoolCreationEntity);
        schoolCreationEntity.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        infoRepo.save(schoolCreationEntity);
        return "Saved Successfully";
    }

    @Override
    public String deleteSchool(UUID id) {
        if (infoRepo.existsById(id)) {
            infoRepo.deleteById(id);
            return "School with ID " + id + " deleted successfully.";
        } else {
            return "School with ID " + id + " not found.";
        }
    }



    @Override
    public String updateSchool(SchoolCreationDto details) {
        try {
            SchoolCreationEntity saveSchool = infoRepo.getById(claimService.getLoggedInUserSchoolCode() , details.getId());
            updateSchoolDetails(saveSchool , details);
            return "School saved successfully";

        }catch (Exception e){
            throw new BadRequestException("Data not found");
        }
    }


    public void convertDtoToEntity(SchoolCreationDto dto , SchoolCreationEntity entity){
        entity.setSchoolName(dto.getSchoolName());
        entity.setSchoolAddress(dto.getSchoolAddress());
    }

    public void updateSchoolDetails(SchoolCreationEntity saveSchool , SchoolCreationDto details){
        saveSchool.setSchoolName(details.getSchoolName());
        saveSchool.setSchoolAddress(details.getSchoolAddress());
    }

}
