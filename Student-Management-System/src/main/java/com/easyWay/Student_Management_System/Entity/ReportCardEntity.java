package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.StudentReportCardDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ReportCardEntity extends BaseEntity{

    private String examType;
    private String examDate;
    @Column(columnDefinition = "text")
    private String subjects;

    private float totalMarks;
    private float average;
    private String grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_info_id", nullable = false)
    @JsonIgnore
    private StudentInfo studentInfo;
}
