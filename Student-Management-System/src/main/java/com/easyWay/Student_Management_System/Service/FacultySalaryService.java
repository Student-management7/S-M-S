package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import org.springframework.stereotype.Service;

import java.util.UUID;


public interface FacultySalaryService  {
    String saveFacultySalary(FacultySalaryDto details);

    String deleteSalary(UUID id);

    String editSalary(FacultySalaryDto dto);
}
