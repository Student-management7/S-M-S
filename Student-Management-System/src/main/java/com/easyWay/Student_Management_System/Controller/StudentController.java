package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.FileTracking;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Service.FacultyService;
import com.easyWay.Student_Management_System.Service.StudentService;
import com.easyWay.Student_Management_System.Utils.EmailCheckUtils;
import lombok.Getter;
import org.apache.coyote.BadRequestException;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    EmailCheckUtils emailCheckUtils;

    @Autowired
    StudentInfoRepo studentInfoRepo;

    @PostMapping("/save")
    public String saveStudent(@RequestBody StudentInfoDto details){
    try {
        return studentService.saveStudent(details);
    } catch (com.easyWay.Student_Management_System.Helper.BadRequestException e) {
        throw e;
    } catch (Exception e) {
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
    public List<StudentInfoDto> findAllStudentBYClass(@RequestParam(required = false) String cls
            ,@RequestParam(required = false) String name, @RequestParam(required = false) UUID id){

        return studentService.getStudentByClass(cls , name, id);
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam UUID id) {return studentService.deleteStudent(id);}

    @PostMapping("/update")
    public String updateStudent(@RequestBody StudentInfoDto student) {
        return studentService.updateStudent(student);
    }

    @GetMapping("/getExcelRecord")
    public List<FileTracking> getStudentById() {
        return studentService.getExcelRecord();
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadExcel(@RequestParam String value, @RequestParam UUID id) throws IOException {
        // Sample Data
        String[] headers = new String[0];
        List<StudentInfo> data = new ArrayList<>();
        if(value.equalsIgnoreCase("error")) {
              headers = new String[]{
                      "Error Description", "Error Code", "Name", "Address", "City", "State", "Father Name", "Mother Name",
                      "Primary Contact", "Secondary Contact", "Family Address", "Family City",
                      "Family State", "Family Email", "Contact", "Gender", "Date of Birth",
                      "Email", "Class", "Department", "Category", "Admission Class"
              };

            data = studentInfoRepo.getByFileId(id);
            if(ObjectUtils.isEmpty(data)){
                throw new com.easyWay.Student_Management_System.Helper.BadRequestException("No data found for the given ID");
            }

        }else if(value.equalsIgnoreCase("success")){
            headers = new String[]{
                    "Name", "Address", "City", "State", "Father Name", "Mother Name",
                    "Primary Contact", "Secondary Contact", "Family Address", "Family City",
                    "Family State", "Family Email", "Contact", "Gender", "Date of Birth",
                    "Email", "Class", "Department", "Category", "Admission Class"
            };

            data = studentInfoRepo.getByFileId(id);
            if(ObjectUtils.isEmpty(data)){
                throw new com.easyWay.Student_Management_System.Helper.BadRequestException("No data found for the given ID");
            }
        } else {
            headers = new String[]{
                    "Name", "Address", "City", "State", "Father Name", "Mother Name",
                    "Primary Contact", "Secondary Contact", "Family Address", "Family City",
                    "Family State", "Family Email", "Contact", "Gender", "Date of Birth",
                    "Email", "Class", "Department", "Category", "Admission Class"
            };

            data = studentInfoRepo.getByFileId(id);
            if(ObjectUtils.isEmpty(data)){
                throw new com.easyWay.Student_Management_System.Helper.BadRequestException("No data found for the given ID");
            }
        }

        byte[] excelFile = emailCheckUtils.generateExcel(data, headers, value);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(excelFile);

    }


}
