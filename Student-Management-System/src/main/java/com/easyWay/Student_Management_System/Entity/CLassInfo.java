package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class CLassInfo extends BaseEntity {

    private String className;

    private String subject;


}