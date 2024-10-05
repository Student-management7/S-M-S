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
import java.util.concurrent.TimeUnit;

import static com.easyWay.Student_Management_System.Helper.ExcelHelper.setColumnValue;


@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentInfoRepo infoRepo;

    @Autowired
    FileTrackingRepo fileTrackingRepo;


   // @Value("${chunckSize")
    static int size = 1000 ;

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
        log.info("Uploading file : {}", filename);
        FileTracking fileTracking = new FileTracking();
        if(FileUtils.isCsv(filename)){

        }
        else if(FileUtils.isExcel(filename)){

            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            if(workbook == null){
                throw new BadRequestException("No record found");
            }

            Sheet sheet = workbook.getSheetAt(0);
            long total = sheet.getLastRowNum();
            if(total < 1){
                throw new BadRequestException("No record found");
            }
            validateHeader(sheet);
            fileTracking =  saveFileTracking(fileTracking, filename, total-1);
            List<StudentInfoDto> students = readExcel(sheet , workbook);
            biffercations(students , fileTracking);
            log.info("biffercations ended !");
        }
        return "Uploaded Successfully";
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
        List<String> errorCodes = new ArrayList<>();
        List<String> errorDescription = new ArrayList<>();
        List<StudentInfo> studentInfos = new ArrayList<>();
        long errorCount = 0;
        for(StudentInfoDto studentDto : students){
            validateMaditeryField(studentDto, errorDescription, errorCodes);
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setFileTracking(fileTracking);
            convertDtoToEntity(studentDto, studentInfo);
            if(!errorCodes.isEmpty() && !errorDescription.isEmpty()){
                studentInfo.setErrorCode(errorCodes.toString().substring(1 ,errorCodes.toString().length()-1));
                studentInfo.setErrorDescription(errorDescription.toString().substring(1 ,errorDescription.toString().length()-1));
                errorCount++;
            }
            studentInfos.add(studentInfo);
        }

        setFileTrackingDetails(fileTracking, studentInfos, errorCount, errorCodes, errorDescription);
        infoRepo.saveAllAndFlush(studentInfos);
    }

    private void setFileTrackingDetails(FileTracking fileTracking, List<StudentInfo> studentInfos, long errorCount, List<String> errorCodes, List<String> errorDescription) {
        fileTracking.setStudentInfo(studentInfos);
        fileTracking.setSuccess(fileTracking.getTotal()- errorCount);
        fileTracking.setFailure(errorCount);
        if(fileTracking.getSuccess() > 1 && fileTracking.getFailure() > 1){
            fileTracking.setFileStatus(FileStatus.PROCESSEDWITHERROR);
        }
        else if(!errorCodes.isEmpty() && !errorDescription.isEmpty()){
            fileTracking.setFileStatus(FileStatus.PROCESSED);
        }else {
            fileTracking.setFileStatus(FileStatus.ERROR);
        }
        fileTrackingRepo.save(fileTracking);
    }

    void validateMaditeryField(StudentInfoDto student, List<String> errorDescription, List<String> errorCodes){
         if(StringUtil.isBlank(student.getName())){
            errorCodes.add("ER201");
            errorDescription.add("Name is required");
         }
       if(StringUtil.isBlank(student.getAddress())){
           errorCodes.add("ER202");
           errorDescription.add("Address is required");
       }
       if(StringUtil.isBlank(student.getDob())){
           errorCodes.add("ER203");
           errorDescription.add("DOB is required");
       }
       if(StringUtil.isBlank(student.getCity())){
           errorCodes.add("ER204");
           errorDescription.add(" City is required");
       }
       if(StringUtil.isBlank(student.getState())){
           errorCodes.add("ER205");
           errorDescription.add("State is required");
       }
       if(StringUtil.isBlank(student.getGender())){
           errorCodes.add("ER206");
           errorDescription.add("Gender is required");
       }

    }

    public  List<StudentInfoDto>  readExcel(Sheet sheet , Workbook workbook) throws Exception {
        List<StudentInfoDto> studentList = new ArrayList<>();



            int rowCount = sheet.getLastRowNum() +1 ;
            int chunkSize  = (rowCount/size) +1;


            ExecutorService executorService  = Executors.newFixedThreadPool(chunkSize);
        try {
            for(int i = 0; i< chunkSize ; i++ ){
                int startIndex = i * size;
                int endIndex = Math.min((i+1) * size, rowCount);
                executorService.submit(() -> {
                    log.info("job started for reading Excel file");
                    for(int j = startIndex; j< endIndex; j++){
                        Row row = sheet.getRow(j);
                        if(row == null || row.getRowNum() ==0){
                            continue;
                        }
                        int k = 0;

                        var studentInfoDto = StudentInfoDto.builder();
                        var familyDetails = FamilyDetails.builder();
                        log.info("line 198");
                        studentInfoDto.name(setColumnValue(row.getCell(k++)));
                        log.info("line 199");
                        studentInfoDto.address(setColumnValue(row.getCell(k++)));
                        studentInfoDto.city(setColumnValue(row.getCell(k++)));
                        studentInfoDto.state(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_FatherName(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_MotherName(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_primaryContact(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_secondaryContact(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_address(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_city(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_state(setColumnValue(row.getCell(k++)));
                        familyDetails.stdo_email(setColumnValue(row.getCell(k++)));
                        studentInfoDto.familyDetails(familyDetails.build());
                        studentInfoDto.contact(setColumnValue(row.getCell(k++)));
                        studentInfoDto.gender(setColumnValue(row.getCell(k++)));
                        studentInfoDto.dob(setColumnValue(row.getCell(k++)));
                        studentInfoDto.email(setColumnValue(row.getCell(k++)));
                        studentInfoDto.cls(setColumnValue(row.getCell(k++)));
                        studentInfoDto.department(setColumnValue(row.getCell(k++)));
                        studentInfoDto.category(setColumnValue(row.getCell(k++)));
                        log.info("info : {}" , studentInfoDto);
                        studentList.add(studentInfoDto.build());
                        log.info("line 222");
                        log.info("info 2 : {}" , studentList);
                    }
                }, executorService);
            }

        } catch (Exception e) {
            log.error("error message : {}", e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
        finally {
            executorService.shutdown();
        }
        fileClose(workbook , executorService);
        log.info("read Excel file success : {}", studentList);
        log.info("Size : {}", studentList.size());
        return studentList;
    }
    private void  fileClose(Workbook workbook , ExecutorService executorService) throws IOException {
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        }
        catch (Exception e) {
            log.error("error message : {}", e.getMessage());

            Thread.currentThread().interrupt();
        }
        finally {
            workbook.close();
        }
    }

    static void  validateHeader(Sheet sheet ) throws BadRequestException {
        Row headerRow = sheet.getRow(0);
        List<String > headList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for(StudendtHeader header : StudendtHeader.values()) {
            Integer index = header.getIndex();
            String  getValue = header.getValue();
            String colValue = dataFormatter.formatCellValue(headerRow.getCell(index));
            headList.add(colValue);
            if (!getValue.equalsIgnoreCase(colValue)){
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

    public FileTracking saveFileTracking(FileTracking fileTracking, String filename, long total) {
        fileTracking.setFileName(filename+ System.currentTimeMillis());
        fileTracking.setTotal(total);
        fileTracking.setFileStatus(FileStatus.IN_PROGRESS);
        fileTracking.setFileType(FileType.EXCEL);
        fileTracking =  fileTrackingRepo.save(fileTracking);
        return fileTracking;
    }


}
