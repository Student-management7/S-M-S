package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data

public class AttendanceRequestDto {
    public String className;
    public String subject;


    public List<AttendanceDto> studentList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<AttendanceDto> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<AttendanceDto> studentList) {
        this.studentList = studentList;
    }
}
