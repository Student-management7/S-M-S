package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultySalaryDto;
import org.springframework.stereotype.Service;


public interface FacultySalaryService {
    String saveFacultySalary(FacultySalaryDto details);
}
