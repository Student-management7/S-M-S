package com.easyWay.Student_Management_System.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class StudentFeeInfo extends BaseEntity{

    private int fee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_info_id", nullable = false)
    private StudentInfo studentInfo;
}
