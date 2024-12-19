package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users extends  BaseEntity{

    private String email;
    private String password;
    private String schoolCode;

    @Column(columnDefinition = "Text")
    private String permission ;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FacultyInfo facultyInfo;
}
