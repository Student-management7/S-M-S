package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.FacultyInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface FacultyService {


    String saveFaculty(FacultyInfoDto details);

    String updateFaculty(FacultyInfoDto faculty);

    String deleteFaculty(UUID id);

    List<FacultyInfoDto> getAllFaculty();


}
