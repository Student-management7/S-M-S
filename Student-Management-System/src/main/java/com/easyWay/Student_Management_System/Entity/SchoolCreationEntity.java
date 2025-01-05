package com.easyWay.Student_Management_System.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "schoolCreation_info")
public class SchoolCreationEntity extends BaseEntity{

    private String schoolName;
    private String registrationNumber;
    private String gstNumber;
    @Column(columnDefinition = "int default 0")
    private int establishmentYear;
    private String schoolAddress;
    private String city;
    private String state;
    private String pincode;
    private String contactNumber;

    @Column(columnDefinition = "int default 0")
    private int numberOfStaff;

    @Column(columnDefinition = "int default 0")
    private int numberOfStudents;
    private String affiliationBoard;

    private String bankAccountDetails;
    private String panNumber;

    @Column(columnDefinition = "int default 0")
    private int numberOfClassrooms;

    @Column(columnDefinition = "int default 0")
    private int numberOfLabs;
    @Column(columnDefinition = "boolean default false")
    private boolean hasPlayground;
    @Column(columnDefinition = "boolean default false")
    private boolean hasLibrary;
    private String mediumOfInstruction;
    private String schoolType;
    @Column(columnDefinition = "boolean default false")
    private boolean hasHostel;
    @Column(columnDefinition = "boolean default false")
    private boolean hasTransport;

    @OneToOne
    @JsonBackReference
    private Users usersInfo;
}
