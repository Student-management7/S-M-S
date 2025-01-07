package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ClassResponseDto;

public interface ClassResponseService {

    ClassResponseDto getData();

    String saveData(ClassResponseDto response);

    String editSubjectInClass(ClassResponseDto details);

    String deleteClass(String  className);
}
