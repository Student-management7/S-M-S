package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StudentService{

    String saveStudent(StudentInfoDto details);

    String studentBulkUpload(MultipartFile file) throws Exception;

}
