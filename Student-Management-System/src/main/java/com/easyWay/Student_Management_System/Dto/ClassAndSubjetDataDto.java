package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ClassAndSubjetDataDto {

    private String className;
    private List<String> subject;

}