package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PermissionsDto {

    public UUID facultyId;
    public permissions permissions;
}
