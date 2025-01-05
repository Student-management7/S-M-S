package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Enums.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "Text")
    private String permission ;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FacultyInfo facultyInfo;

    @OneToOne(mappedBy = "usersInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private SchoolCreationEntity schoolCreation;
}
