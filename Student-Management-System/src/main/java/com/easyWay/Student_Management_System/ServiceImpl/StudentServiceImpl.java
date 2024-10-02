package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoRepo infoRepo;


    @Override
    public String saveStudent(StudentInfoDto details) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setName(details.getName());
        studentInfo.setSurName(details.getSurName());
        studentInfo.setEmailId(details.getEmailId());
        studentInfo.setPhoneNumber(details.getPhoneNumber());
        infoRepo.save(studentInfo);
        return "Saved Successfully";
    }
}
