package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Service.StudentService;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentInfoDto details){

      return studentService.saveStudent(details);
    }
    @PostMapping("/bulkupload")
    public ResponseEntity<String> bulkUploadStudent(@RequestParam("file") MultipartFile file){

        try {
            return ResponseEntity.ok(studentService.studentBulkUpload(file));
        }
        catch (Exception e){
            try {
                throw new BadRequestException(String.valueOf(Map.of("message" ,e.getMessage())));
            } catch (BadRequestException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @GetMapping("/findAllStudent")
    public List<StudentInfoDto> findAllStudentBYClass(){

        return studentService.getStudentByClass();
    }
}
