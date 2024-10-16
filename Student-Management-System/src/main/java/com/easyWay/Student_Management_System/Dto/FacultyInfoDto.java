package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Id;
import lombok.Data;

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

    public FactGraduation fact_graduation;

    public FactPostGraduation fact_postGraduation;

    public FactOther fact_other;

    public FactCls fact_cls;

    public FactStatus fact_status;

    public UUID getFact_id() {
        return fact_id;
    }

    public void setFact_id(UUID fact_id) {
        this.fact_id = fact_id;
    }

    public String getFact_Name() {
        return fact_Name;
    }

    public void setFact_Name(String fact_Name) {
        this.fact_Name = fact_Name;
    }

    public String getFact_email() {
        return fact_email;
    }

    public void setFact_email(String fact_email) {
        this.fact_email = fact_email;
    }

    public String getFact_contact() {
        return fact_contact;
    }

    public void setFact_contact(String fact_contact) {
        this.fact_contact = fact_contact;
    }

    public String getFact_gender() {
        return fact_gender;
    }

    public void setFact_gender(String fact_gender) {
        this.fact_gender = fact_gender;
    }

    public String getFact_address() {
        return fact_address;
    }

    public void setFact_address(String fact_address) {
        this.fact_address = fact_address;
    }

    public String getFact_city() {
        return fact_city;
    }

    public void setFact_city(String fact_city) {
        this.fact_city = fact_city;
    }

    public String getFact_state() {
        return fact_state;
    }

    public void setFact_state(String fact_state) {
        this.fact_state = fact_state;
    }

    public String getFact_joiningDate() {
        return fact_joiningDate;
    }

    public void setFact_joiningDate(String fact_joiningDate) {
        this.fact_joiningDate = fact_joiningDate;
    }

    public String getFact_leavingDate() {
        return fact_leavingDate;
    }

    public void setFact_leavingDate(String fact_leavingDate) {
        this.fact_leavingDate = fact_leavingDate;
    }

    public FactGraduation getFact_graduation() {
        return fact_graduation;
    }

    public void setFact_graduation(FactGraduation fact_graduation) {
        this.fact_graduation = fact_graduation;
    }

    public FactPostGraduation getFact_postGraduation() {
        return fact_postGraduation;
    }

    public void setFact_postGraduation(FactPostGraduation fact_postGraduation) {
        this.fact_postGraduation = fact_postGraduation;
    }

    public FactOther getFact_other() {
        return fact_other;
    }

    public void setFact_other(FactOther fact_other) {
        this.fact_other = fact_other;
    }

    public FactCls getFact_cls() {
        return fact_cls;
    }

    public void setFact_cls(FactCls fact_cls) {
        this.fact_cls = fact_cls;
    }

    public FactStatus getFact_status() {
        return fact_status;
    }

    public void setFact_status(FactStatus fact_status) {
        this.fact_status = fact_status;
    }
}
