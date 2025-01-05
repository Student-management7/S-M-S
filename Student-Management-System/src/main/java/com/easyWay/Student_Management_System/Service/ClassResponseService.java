package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ClassResponseDto;

import java.util.List;
import java.util.UUID;

public interface ClassResponseService {

    ClassResponseDto getData();

    String saveData(ClassResponseDto response);

    String editSubjectInClass(ClassResponseDto details);

    String deleteClass(UUID id);
}
