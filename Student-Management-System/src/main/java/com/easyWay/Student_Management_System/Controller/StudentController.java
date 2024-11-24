package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Service.FacultyService;
import com.easyWay.Student_Management_System.Service.StudentService;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @CrossOrigin(origins = "https://student-management-system-gui-3.onrender.com", allowCredentials = "true")
    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentInfoDto details){
    try {
        return studentService.saveStudent(details);
    } catch (com.easyWay.Student_Management_System.Helper.BadRequestException e) {
        throw e;
    } catch (Exception e) {
        // Wrap other exceptions to be handled by GlobalExceptionHandler
        throw new RuntimeException("An error occurred while saving the student: " + e.getMessage());
    }
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
    public List<StudentInfoDto> findAllStudentBYClass(@RequestParam(required = false) String cls){

        return studentService.getStudentByClass(cls);
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam UUID id) {return studentService.deleteStudent(id);}

    @PostMapping("/update")
    public String updateStudent(@RequestBody StudentInfoDto student){
        return studentService.updateStudent(student);
    }



}
