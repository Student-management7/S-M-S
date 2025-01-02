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
    private float schoolFee;
    private float sportsFee;
    private float bookFee;
    private float transportation;
    @Column(columnDefinition = "Text")
    private String otherAmount;
    private float total;
}
