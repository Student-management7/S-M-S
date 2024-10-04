package com.easyWay.Student_Management_System.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.FileNameUtils;

@Slf4j
public class FileUtils {

   public static boolean  isCsv(String file ){
        log.info("isCsv file is : {} ", FileNameUtils.getExtension(file));
        return "csv".equalsIgnoreCase(FileNameUtils.getExtension(file));
    }

  public static boolean isExcel(String file ){
        log.info("isExcel file is : {} ", FileNameUtils.getExtension(file));
        return "xlsx".equalsIgnoreCase(FileNameUtils.getExtension(file));
    }
}
