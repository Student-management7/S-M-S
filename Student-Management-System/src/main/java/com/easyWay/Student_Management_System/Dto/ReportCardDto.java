package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.StudentInfo;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ReportCardDto {

   public UUID id;
   public UUID reportId;
   public String examType;
   public String examDate;

   List<StudentReportCardDto> subjects;

   public float totalMarks;
   public float average;
   public String grade;
   public StudentInfo studentInfo;


}
