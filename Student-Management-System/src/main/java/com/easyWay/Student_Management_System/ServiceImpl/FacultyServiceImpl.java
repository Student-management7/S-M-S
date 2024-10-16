package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Repo.FacultyInfoRepo;
import com.easyWay.Student_Management_System.Service.FacultyService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@Data
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    FacultyInfoRepo infoRepo;
    @Override
    public String saveFaculty(FacultyInfoDto details) {
        FacultyInfo facultyInfo = new FacultyInfo();

        convertDtoToEntity(details, facultyInfo);

        infoRepo.save(facultyInfo);

        return "Saved Successfully";

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

        entity.setFact_graduation(dto.getFact_graduation().toString());
        entity.setFact_postGraduation(dto.getFact_postGraduation().toString());
        entity.setFact_other(dto.getFact_other().toString());
        entity.setFact_cls(dto.getFact_cls().toString());
        entity.setFact_status(dto.getFact_status().toString());


    }
}
