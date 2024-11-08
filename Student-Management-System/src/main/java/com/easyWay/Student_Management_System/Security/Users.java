package com.easyWay.Student_Management_System.Security;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class Users {
    private UUID id;
    private LocalDateTime creationDateTime ;
    private String email;
    private String password;

}
