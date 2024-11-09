package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Data
public class StudentAttendance extends BaseEntity{

    private String className;
    private String subject;
    private LocalDateTime creationDateTime = LocalDateTime.now();

    @Column(columnDefinition = "text")
    private String studentList;


}
