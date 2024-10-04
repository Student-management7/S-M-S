package com.easyWay.Student_Management_System.ServiceImpl;

import com.easyWay.Student_Management_System.Dto.FamilyDetails;
import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.FileTracking;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import com.easyWay.Student_Management_System.Enums.FileStatus;
import com.easyWay.Student_Management_System.Enums.FileType;
import com.easyWay.Student_Management_System.Enums.StudendtHeader;
import com.easyWay.Student_Management_System.Helper.ExcelHelper;
import com.easyWay.Student_Management_System.Repo.FileTrackingRepo;
import com.easyWay.Student_Management_System.Repo.StudentInfoRepo;
import com.easyWay.Student_Management_System.Service.StudentService;
import com.easyWay.Student_Management_System.Utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoRepo infoRepo;

    @Autowired
    FileTrackingRepo fileTrackingRepo;


    @Value("${chunckSize}")
    static int size ;

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
        FileTracking fileTracking = new FileTracking();
        if(FileUtils.isCsv(filename)){

        }
        else if(FileUtils.isExcel(filename)){

            List<StudentInfoDto> students = readExcel(file.getInputStream() ,fileTracking);
            fileTracking.setFileName(filename+ System.currentTimeMillis());
            fileTracking.setTotal((long) students.size());
            fileTracking.setFileStatus(FileStatus.IN_PROGRESS);
            fileTrackingRepo.save(fileTracking);
            biffercations(students , fileTracking);

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

    public void biffercations(List<StudentInfoDto> students , FileTracking fileTracking){
        for(StudentInfoDto studentDto : students){
            validateMaditeryField(studentDto);
            StudentInfo studentInfo = new StudentInfo();
            convertDtoToEntity(studentDto, studentInfo);
            infoRepo.save(studentInfo);
        }
        fileTracking.setFileStatus(FileStatus.PROCESSED);
        fileTrackingRepo.save(fileTracking);
    }
   void validateMaditeryField(StudentInfoDto student){
         if(StringUtil.isBlank(student.getName())){
             student.getErrorCodes().add("ER201");
             student.getErrorDescription().add("Er: Name is required");
         }
       if(StringUtil.isBlank(student.getAddress())){
           student.getErrorCodes().add("ER202");
           student.getErrorDescription().add("Er: Address is required");
       }
       if(StringUtil.isBlank(student.getDob())){
           student.getErrorCodes().add("ER203");
           student.getErrorDescription().add("Er: DOB is required");
       }
       if(StringUtil.isBlank(student.getCity())){
           student.getErrorCodes().add("ER204");
           student.getErrorDescription().add("Er: City is required");
       }
       if(StringUtil.isBlank(student.getState())){
           student.getErrorCodes().add("ER205");
           student.getErrorDescription().add("Er: State is required");
       }
       if(StringUtil.isBlank(student.getGender())){
           student.getErrorCodes().add("ER206");
           student.getErrorDescription().add("Er: Gender is required");
       }



    }

    public  List<StudentInfoDto>  readExcel(InputStream file , FileTracking fileTracking) throws Exception {
        List<StudentInfoDto> studentList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        fileTracking.setFileType(FileType.EXCEL);
        fileTracking.setFileStatus(FileStatus.UPLOADED);
        fileTrackingRepo.save(fileTracking);

        try (Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            validateHeader(headerRow);
            int rowCount = sheet.getLastRowNum() +1 ;
            int chunkSize  = (rowCount/size) +1;
            ExecutorService executorService  = Executors.newFixedThreadPool(chunkSize);

            for(int i = 0; i< chunkSize ; i++ ){
                int startIndex = i*size;
                int endIndex = Math.min((i+1)*size, chunkSize);
                executorService.submit(() -> {
                    log.info("job started for reading Excel file");
                    for(int j = startIndex; j< endIndex; j++){
                        Row row = sheet.getRow(j);
                        if(row == null || row.getRowNum() ==0){
                            continue;
                        }
                        var studentInfoDto = StudentInfoDto.builder();
                        var familyDetails = FamilyDetails.builder();
                        int k =0;
                        studentInfoDto.name(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.address(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.city(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.state(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_FatherName(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_MotherName(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_primaryContact(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_secondaryContact(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_address(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_city(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_state(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        familyDetails.stdo_email(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.familyDetails(familyDetails.build());
                        studentInfoDto.contact(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.gender(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.dob(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.email(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.cls(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.department(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                        studentInfoDto.category(dataFormatter.formatCellValue(headerRow.getCell(k++)));

                        studentList.add(studentInfoDto.build());
                    }
                });
            }

        } catch (Exception e) {
            log.error("error message : {} "+e.getMessage());
        }

        return null;
    }

    static void  validateHeader(Row headerRow ) throws BadRequestException {
        List<String > headList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for(StudendtHeader header : StudendtHeader.values()) {
            Integer index = header.getIndex();
            String  getValue = header.getValue();
            String colValue = dataFormatter.formatCellValue(headerRow.getCell(index));
            headList.add(colValue);
            if (getValue.equalsIgnoreCase(colValue)){
                throw new BadRequestException("Please upload the correct format !");
            }
        }
        for ( int i =0 ; i<headerRow.getLastCellNum(); i++) {
            String colValue = dataFormatter.formatCellValue(headerRow.getCell(i));
            if(!headList.contains(colValue)){
                throw new BadRequestException("Please upload the correct format !");
            }
        }
    }


}
