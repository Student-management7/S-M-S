package com.easyWay.Student_Management_System.Helper;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Enums.StudendtHeader;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExcelHelper {
    //take form property
    @Value("${chunckSize}")
   static int size ;

    public static List<StudentInfoDto>  readExcel(InputStream  file) throws Exception {
        List<StudentInfoDto> studentList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
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
                     int k =0;
                     studentInfoDto.name(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                     studentInfoDto.address(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                     studentInfoDto.city(dataFormatter.formatCellValue(headerRow.getCell(k++)));
                     studentInfoDto.state(dataFormatter.formatCellValue(headerRow.getCell(k++)));
//                     studentInfoDto.familyDetails(dataFormatter.formatCellValue(headerRow.getCell(k++)));
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
