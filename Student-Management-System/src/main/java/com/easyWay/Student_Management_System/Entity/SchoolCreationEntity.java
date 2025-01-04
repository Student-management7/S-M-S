package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
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
    private String schoolAddress;

}
