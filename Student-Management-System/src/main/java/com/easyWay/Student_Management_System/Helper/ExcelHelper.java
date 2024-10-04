package com.easyWay.Student_Management_System.Helper;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    int size = 10;

    public  List<StudentInfoDto> readExcel(InputStream  file, Enum headers) throws Exception {
        List<StudentInfoDto> dataList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        try (Workbook workbook = new XSSFWorkbook(file)) {

            Sheet sheet = workbook.getSheetAt(0); // Reading the first sheet
            Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
            validateHeader(headerRow , headers);
            int rowCount = sheet.getLastRowNum() +1 ;
            int chunkSize  = (rowCount/size) +1;
            ExecutorService executorService  = Executors.newFixedThreadPool(chunkSize);

            for(int i = 0; i< chunkSize ; i++ ){
                int startIndex = i*size;
                int endIndex = Math.min((i+1)*size, chunkSize);
                ExecutorService.submit(()->{
                 log.info("job started for reading Excel file");
                 for(int j = startIndex; j< endIndex; j++){
                     Row row = sheet.getRow(j);
                     if(row == null || row.getRowNum() ==0){
                         continue;
                     }
                     var studentInfoDto = StudentInfoDto.builder();
                     int k =0;
                     studentInfoDto.name(dataFormatter.formatCellValue(headerRow.getCell(index)))


                 }
                });
            }

        } catch (Exception e) {
            log.error("error message : {} "+e.getMessage());
        }


        return null;
    }

    void validateHeader(Row headerRow ,  Enum headers) throws BadRequestException {
        List<String > headList = new ArrayList<>();
        DataFormatter dataFormatter = new DataFormatter();
        for(Enum<?> header : headers.values()) {
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
