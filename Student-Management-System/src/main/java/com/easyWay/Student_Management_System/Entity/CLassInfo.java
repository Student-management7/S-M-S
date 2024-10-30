package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class CLassInfo extends BaseEntity {

    private String schoolName;

    private String className;

    private String subject;

}