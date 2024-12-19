package com.easyWay.Student_Management_System.Dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.UUID;

@Data
public class PermissionsDto {

    public UUID facultyId;
    public permissions permissions;
}
