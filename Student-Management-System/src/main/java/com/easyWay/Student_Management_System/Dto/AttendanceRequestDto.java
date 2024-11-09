package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.time.LocalDateTime;
import java.util.List;
@Data

public class AttendanceRequestDto {
    public String className;
    public String subject;
    public String date;
    public List<AttendanceDto> studentList;

}
