package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.OtherFeesDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class AdminFeesStructure extends BaseEntity{

    private String className;
    private int schoolFee;
    private int sportsFee;
    private int bookFee;
    private int transportation;
    @Column(columnDefinition = "Text")
    private String otherAmount;
    private int total;
}
