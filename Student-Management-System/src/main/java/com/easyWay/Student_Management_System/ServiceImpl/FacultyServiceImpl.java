package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.FactQualificationDto;
import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Service.FacultyService;
import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String saveFaculty(FacultyInfoDto details) {
        FacultyInfo facultyInfo = new FacultyInfo();

        convertDtoToEntity(details, facultyInfo);

        infoRepo.save(facultyInfo);

        return "Saved Successfully";

    }


    @Override
    public String updateFaculty(FacultyInfoDto faculty) {
        try {
            FacultyInfo saveFaculty = infoRepo.getById(faculty.getFact_id());
            updateFacultyDetails( saveFaculty, faculty);
            return "Faculty saved successfully";
        }catch (Exception e){
            System.out.println("data not found");
            return "Data not found";
        }
    }

    @Override
    public String deleteFaculty(UUID id) {
        try {
            FacultyInfo entity = infoRepo.getById(id);
            entity.setDelete(true);
            infoRepo.save(entity);
            return "Deleted successfully";

        }catch (Exception e){
            System.out.println("Not deleted");
            return "Not deleted successfully";

        }

    }


    void updateFacultyDetails(FacultyInfo saveFaculty , FacultyInfoDto details){
        saveFaculty.setFact_id(details.getFact_id());
        saveFaculty.setFact_Name(details.getFact_Name());
        saveFaculty.setFact_cls(details.getFact_Cls().toString());
        saveFaculty.setFact_state(details.getFact_state());
        saveFaculty.setFact_status(details.getFact_Status());
        saveFaculty.setFact_city(details.getFact_city());
        saveFaculty.setFact_address(details.getFact_address());
        saveFaculty.setFact_gender(details.getFact_gender());
        saveFaculty.setFact_contact(details.getFact_contact());
        saveFaculty.setFact_joiningDate(details.getFact_joiningDate());
        saveFaculty.setFact_leavingDate(details.getFact_leavingDate());

        for ( FactQualificationDto qualificationDto:details.getFact_qualification()){
           saveFaculty.setFact_graduation(gson.toJson(qualificationDto.getFact_Graduation()));
           saveFaculty.setFact_postGraduation(gson.toJson(qualificationDto.getFact_PostGraduation()));
           saveFaculty.setFact_other(gson.toJson(qualificationDto.Fact_0ther));
        }
      infoRepo.save(saveFaculty);
    }

    private void convertDtoToEntity(FacultyInfoDto dto, FacultyInfo entity){

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
        for ( FactQualificationDto qualificationDto:dto.getFact_qualification()){
            entity.setFact_graduation(gson.toJson(qualificationDto.getFact_Graduation()));
            entity.setFact_postGraduation(gson.toJson(qualificationDto.getFact_PostGraduation()));
            entity.setFact_other(gson.toJson(qualificationDto.Fact_0ther));
        }

        entity.setFact_cls(dto.getFact_Cls().toString());
        entity.setFact_status(dto.getFact_Status());


    }
}
