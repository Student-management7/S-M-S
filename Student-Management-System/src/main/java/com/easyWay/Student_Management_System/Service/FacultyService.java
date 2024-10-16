package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface FacultyService {

    String saveFaculty(FacultyInfoDto details);
}
