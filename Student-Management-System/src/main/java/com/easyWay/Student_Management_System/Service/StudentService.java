package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudentService{

    String saveStudent(StudentInfoDto details);

    String studentBulkUpload(MultipartFile file) ;

    List<StudentInfo> getStudentByClass();

}
