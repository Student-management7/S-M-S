package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Users extends  BaseEntity{

    private String email;
    private String password;
    private String schoolCode;
    private String permission ;

}
