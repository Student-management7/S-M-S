package com.easyWay.Student_Management_System.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class AdminCreationEntity extends BaseEntity{

    private String name;
    private String role;
    @Column(columnDefinition = "text")
    private String assignedSchools;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id", nullable = false)
    @JsonIgnore
    private Users userInfo3;

}
