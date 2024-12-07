package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentFeesDto {
    public UUID id;
    public int fee;
}
