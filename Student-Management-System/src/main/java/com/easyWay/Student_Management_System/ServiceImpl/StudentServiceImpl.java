package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Helper.ExcelHelper;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Service.StudentService;
import com.easyWay.Student_Management_System.Utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.easyWay.Student_Management_System.Helper.ExcelHelper.readExcel;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoRepo infoRepo;


    @Override
    public String saveStudent(StudentInfoDto details) {

        StudentInfo studentInfo = new StudentInfo();
        convertDtoToEntity(details, studentInfo);
        infoRepo.save(studentInfo);
        return "Saved Successfully";
    }

    @Override
    public String studentBulkUpload(MultipartFile file) throws Exception {
        String filename = file.getOriginalFilename();
        log.info("Uploading file " + filename);
        if(FileUtils.isCsv(filename)){

        }
        else if(FileUtils.isExcel(filename)){

            List<StudentInfoDto> students = readExcel(file.getInputStream());
        }
        return null;
    }

    private void convertDtoToEntity(StudentInfoDto dto, StudentInfo entity){
        entity.setName(dto.getName());
        entity.setCity(dto.getCity());
        entity.setAddress(dto.getAddress());
        entity.setStd_state(dto.getState() );
        entity.setFamilyDetails(dto.getFamilyDetails().toString());
        entity.setContact(dto.getContact());
        entity.setGender(dto.getGender());
        entity.setCls(dto.getCls());
        entity.setDepartment(dto.getDepartment());
        entity.setCategory(dto.getCategory());
        entity.setEmail(dto.getEmail());
        entity.setDob(dto.getDob());
    }

}
