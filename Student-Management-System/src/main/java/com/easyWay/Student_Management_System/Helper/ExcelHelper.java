package com.easyWay.Student_Management_System.Helper;

import com.easyWay.Student_Management_System.Dto.FamilyDetails;
import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.FileTracking;
import com.easyWay.Student_Management_System.Enums.FileStatus;
import com.easyWay.Student_Management_System.Enums.FileType;
import com.easyWay.Student_Management_System.Enums.StudendtHeader;
import com.easyWay.Student_Management_System.Repo.FileTrackingRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

   public static String setColumnValue(Cell cell) {
        if (cell == null) {
          return "";
        }else {
          DataFormatter dataFormatter = new DataFormatter();
          String value = dataFormatter.formatCellValue(cell);
          return StringUtil.isBlank(value) ? "" : value.trim();
        }
   }
  }
