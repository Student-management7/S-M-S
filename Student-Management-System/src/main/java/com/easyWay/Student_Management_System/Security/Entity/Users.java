package com.easyWay.Student_Management_System.Security.Entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users extends BaseEntity{

    private String email;
    private String password;

}
