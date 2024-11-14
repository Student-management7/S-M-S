package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class NotificationEntity extends BaseEntity{

    private String startDate;
    private String endDate;
    private String description;
    private String cato;
    private String className;
}