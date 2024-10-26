package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface StudentService{

    String saveStudent(StudentInfoDto details);

    String studentBulkUpload(MultipartFile file) ;

    List<StudentInfoDto> getStudentByClass();

    String deleteStudent(UUID id);
}
