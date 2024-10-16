package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactGraduation {
    private String grd_sub;
    private String grd_branch;
    private String grd_grade;
    private String grd_university;
    private String grd_yearOfPassing;

    @Override
    public String toString() {
        return "FactGraduation{" +
                "grd_sub='" + grd_sub + '\'' +
                ", grd_branch='" + grd_branch + '\'' +
                ", grd_grade='" + grd_grade + '\'' +
                ", grd_university='" + grd_university + '\'' +
                ", grd_yearOfPassing='" + grd_yearOfPassing + '\'' +
                '}';
    }

    public String getGrd_sub() {
        return grd_sub;
    }

    public void setGrd_sub(String grd_sub) {
        this.grd_sub = grd_sub;
    }

    public String getGrd_branch() {
        return grd_branch;
    }

    public void setGrd_branch(String grd_branch) {
        this.grd_branch = grd_branch;
    }

    public String getGrd_grade() {
        return grd_grade;
    }

    public void setGrd_grade(String grd_grade) {
        this.grd_grade = grd_grade;
    }

    public String getGrd_university() {
        return grd_university;
    }

    public void setGrd_university(String grd_university) {
        this.grd_university = grd_university;
    }

    public String getGrd_yearOfPassing() {
        return grd_yearOfPassing;
    }

    public void setGrd_yearOfPassing(String grd_yearOfPassing) {
        this.grd_yearOfPassing = grd_yearOfPassing;
    }
}
