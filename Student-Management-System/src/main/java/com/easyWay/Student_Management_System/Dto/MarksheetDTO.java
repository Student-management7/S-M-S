package com.easyWay.Student_Management_System.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MarksheetDTO {
    private String studentName;
    private String rollNo;
    private String studentClass;
    private String academicYear;
    private List<SubjectMark> subjects;
    private String result;
    private String remarks;
    private String date;
}
