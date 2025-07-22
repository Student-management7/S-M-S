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
    private String role;
    private boolean isActive = true;

    @Column(columnDefinition = "Text")
    private String permission ;

    @OneToOne(mappedBy = "userInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FacultyInfo facultyInfo;

    @OneToOne(mappedBy = "userInfo2", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SchoolCreationEntity schoolCreationEntity;

    @OneToOne(mappedBy = "userInfo3", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AdminCreationEntity adminCreationEntity;


    @OneToOne(mappedBy = "userInfo4", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HotelCreationEntity hotelCreationEntity;
}
