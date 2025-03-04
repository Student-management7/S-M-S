package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class FacultySalaryDto {
    private UUID id;
    private UUID facultyID;
    private float facultySalary;
    private float  facultyTax;
    private float facultyTransport;
    private List<DeductionDto> facultyDeduction;
}
