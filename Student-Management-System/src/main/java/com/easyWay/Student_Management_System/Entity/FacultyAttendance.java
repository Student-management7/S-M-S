package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class FacultyAttendance extends BaseEntity{

    @Column(columnDefinition = "text")
    private String facultyList;

}
