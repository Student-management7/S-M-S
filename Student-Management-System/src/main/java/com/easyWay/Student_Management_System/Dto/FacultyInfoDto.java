package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class FacultyInfoDto {
    @Id
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

    public String Fact_Status;

    public List<FactCls> Fact_Cls;

}
