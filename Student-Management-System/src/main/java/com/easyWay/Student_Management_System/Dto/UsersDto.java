package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class UsersDto {
    private String email;
    private String password;
    private String schoolCode;
    private String permission;
}
