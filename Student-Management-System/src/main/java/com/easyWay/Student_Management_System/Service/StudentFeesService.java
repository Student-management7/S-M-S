package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.StudentFeesDto;

import java.util.UUID;

public interface StudentFeesService {

    String saveStudentFees(StudentFeesDto studentFees);

    String deleteFees(UUID id);

    String editFees(StudentFeesDto dto);
}
