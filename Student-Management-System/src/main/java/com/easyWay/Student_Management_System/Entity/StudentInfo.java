package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class StudentInfo {

    @Id
    public UUID id;
    public String name;
    public String surName;
    public String phoneNumber;
    public String emailId;
    public String Class;
    public int age;

}
