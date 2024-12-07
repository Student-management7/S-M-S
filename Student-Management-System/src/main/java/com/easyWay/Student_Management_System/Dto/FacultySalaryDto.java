package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class FacultySalaryDto {
    private UUID facultyID;
    private int facultySalary;
    private int facultyTax;
    private int facultyTransport;
    private List<DeductionDto> facultyDeduction;
}
