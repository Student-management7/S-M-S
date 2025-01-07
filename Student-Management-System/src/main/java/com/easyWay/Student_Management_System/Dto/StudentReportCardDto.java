package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.StudentInfo;
import lombok.Data;

@Data
public class StudentReportCardDto {
    public String subject;
    public float marksObtained;
    public float maxMarks;
    public String remarks;

}
