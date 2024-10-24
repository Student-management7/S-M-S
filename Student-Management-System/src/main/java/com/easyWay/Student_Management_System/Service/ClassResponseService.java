package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.ClassResponseDto;

import java.util.List;

public interface ClassResponseService {

    ClassResponseDto getData();
    String saveData(ClassResponseDto response);
}
