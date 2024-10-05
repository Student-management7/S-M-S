package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.FamilyDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_info")
public class StudentInfo extends BaseEntity {

    private String name;
    private String address;
    private String city;
    private String std_state;
    @Column(columnDefinition = "text")
    private String familyDetails;
    private String contact;
    private String gender;
    private String dob;
    private String email;
    private String cls;
    private String department;
    private String category;
    private String creation;
    private String errorCode;
    @Column(columnDefinition = "text")
    private String errorDescription;
    private boolean isDelete = false;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FileTracking fileTracking;

}
