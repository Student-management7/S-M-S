package com.easyWay.Student_Management_System.Dto;

import com.easyWay.Student_Management_System.Entity.FacultySalaryEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FacultyInfoDto {

    public UUID fact_id;

    public String fact_Name;
    public String fact_email;
    public String fact_contact;
    public String fact_gender;
    public String fact_address;
    public String fact_city;
    public String fact_state;
    public String fact_joiningDate;
    public String fact_leavingDate;

    public List<FactQualificationDto> fact_qualification;
    @JsonProperty("Fact_Status")
    public String Fact_Status;

    @JsonProperty("Fact_Cls")
    public List<FactCls> Fact_Cls;

    public List<FacultySalaryEntity> fact_salary;

    public String email;
    public String password;
}
