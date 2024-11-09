package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class HolidayInfo extends BaseEntity{

    @Column(columnDefinition = "Text")
    private String className;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String description;
}
