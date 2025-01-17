package com.easyWay.Student_Management_System.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schoolCreation_info")
public class SchoolCreationEntity extends BaseEntity{
    private String schoolName;
    private String schoolAddress;
    private String address;
    private String adminContact;
    private LocalDateTime serviceStartDate;
    private String currentPlan;
    private LocalDateTime renewalDate;
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id", nullable = false)
    @JsonIgnore
    private Users userInfo2;


//    @OneToOne(mappedBy = "adminCreation_info", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private AdminCreationEntity adminCreationEntity;


}
