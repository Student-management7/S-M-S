package com.easyWay.Student_Management_System.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FactStatus {
    private String fact_status;

    @Override
    public String toString() {
        return "FactStatus{" +
                "fact_status='" + fact_status + '\'' +
                '}';
    }

    public String getFact_status() {
        return fact_status;
    }

    public void setFact_status(String fact_status) {
        this.fact_status = fact_status;
    }
}
