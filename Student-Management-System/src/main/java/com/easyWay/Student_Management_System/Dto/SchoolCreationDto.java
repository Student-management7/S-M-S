package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SchoolCreationDto {
    public String schoolName;
    public String schoolAddress;
    public UUID id;
}
