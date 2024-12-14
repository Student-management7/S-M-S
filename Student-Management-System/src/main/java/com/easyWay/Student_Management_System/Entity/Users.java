package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Users extends  BaseEntity{

    private String email;
    private String password;
    private String schoolCode;
    private String permission ;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FacultyInfo facultyInfo;
}
