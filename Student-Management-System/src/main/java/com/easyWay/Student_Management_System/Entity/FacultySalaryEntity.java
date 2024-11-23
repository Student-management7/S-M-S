package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.DeductionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Entity
@Data
public class FacultySalaryEntity extends BaseEntity{
    private int facultySalary;
    private int facultyTax;
    private int facultyTransport;

    @Column(columnDefinition = "Text")
    private String facultyDeduction;
    private int total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_info_id", nullable = false)
    @JsonIgnore
    private FacultyInfo facultyInfo;

}
