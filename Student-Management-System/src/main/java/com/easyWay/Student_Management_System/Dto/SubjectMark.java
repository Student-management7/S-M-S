package com.easyWay.Student_Management_System.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SubjectMark {
    private String name;
    private int q;
    private int h;
    private int f;


    public int getTotal() {
        return q + h + f;
    }

    public String getGrade() {
        int total = getTotal();
        if (total >= 270) return "A+";
        if (total >= 240) return "A";
        if (total >= 210) return "B+";
        return "B";
    }
}
