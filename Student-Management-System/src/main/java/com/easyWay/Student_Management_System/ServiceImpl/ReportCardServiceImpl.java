package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.ReportCardDto;
import com.easyWay.Student_Management_System.Dto.StudentReportCardDto;
import com.easyWay.Student_Management_System.Entity.ReportCardEntity;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Helper.BadRequestException;
import com.easyWay.Student_Management_System.Repo.ReportCardRepo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Service.ReportCardService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ReportCardServiceImpl implements ReportCardService {

    @Autowired
    ReportCardRepo infoRepo;

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @Autowired
    Gson gson;

    @Autowired
    ClaimService claimService;


    @Override
    public String saveReportCard(ReportCardDto dto) {

        StudentInfo studentInfo = studentInfoRepo.getById(dto.getId());

        if(ObjectUtils.isEmpty(studentInfo)){
            throw new BadRequestException("No student found");
        }

        ReportCardEntity entity = new ReportCardEntity();
        entity.setAverage(dto.getAverage());
        entity.setGrade(dto.getGrade());
        entity.setSubjects(gson.toJson(dto.getSubjects()));
        entity.setExamDate(dto.getExamDate());
        entity.setExamType(dto.getExamType());
        entity.setTotalMarks(dto.getTotalMarks());
        entity.setStudentInfo(studentInfo);
        entity.setSchoolCode(claimService.getLoggedInUserSchoolCode());
        infoRepo.save(entity);

        return "save successfully";
    }

    @Override
    public String editReportService(ReportCardDto dto) {


        ReportCardEntity entity = infoRepo.getById(dto.getReportId());


        if(ObjectUtils.isEmpty(entity)){
            throw new BadRequestException("No Data found for the given id");
        }

        entity.setAverage(dto.getAverage());
        entity.setGrade(dto.getGrade());
        entity.setSubjects(gson.toJson(dto.getSubjects()));
        entity.setExamDate(dto.getExamDate());
        entity.setExamType(dto.getExamType());
        entity.setTotalMarks(dto.getTotalMarks());
        infoRepo.save(entity);
        return "data edit successfully";
    }

    @Override
    public List<ReportCardDto> getReportCard(UUID id) {

        List<ReportCardEntity> entity;

        if(ObjectUtils.isEmpty(id)){
            entity = infoRepo.findAll(claimService.getLoggedInUserSchoolCode());
        }else {
            entity = infoRepo.findBySchoolCodeAndStudentInfoId(claimService.getLoggedInUserSchoolCode(), id);
        }

        if (ObjectUtils.isEmpty(entity)){
            throw new BadRequestException("enter a valid id");
        }
        List<ReportCardDto> dtos = new ArrayList<>();
        for (ReportCardEntity entity1: entity){

            ReportCardDto dto = new ReportCardDto();
            dto.setId(entity1.getStudentInfo().getId());
            dto.setReportId(entity1.getId());

            Type studentReportCard = new TypeToken<List<StudentReportCardDto>>() {}.getType();
            dto.setSubjects(gson.fromJson(entity1.getSubjects() , studentReportCard));
            dto.setGrade(entity1.getGrade());
            dto.setExamType(entity1.getExamType());
            dto.setExamDate(entity1.getExamDate().toString());
            dto.setAverage(entity1.getAverage());
            dto.setTotalMarks(entity1.getTotalMarks());
            dto.setStudentInfo(entity1.getStudentInfo());

            dtos.add(dto);
        }

        return dtos;
    }
}
