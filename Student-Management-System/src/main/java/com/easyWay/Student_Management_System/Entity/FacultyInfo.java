package com.easyWay.Student_Management_System.Entity;

import com.easyWay.Student_Management_System.Dto.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class FacultyInfo {

    @Id
    private UUID fact_id;
    private String fact_Name;
    private String fact_email;
    private String fact_contact;
    private String fact_gender;
    private String fact_address;
    private String fact_city;
    private String fact_state;
    private String fact_joiningDate;
    private String fact_leavingDate;

   @Column(columnDefinition = "text")
   private String fact_graduation;

   @Column(columnDefinition = "text")
   private String fact_postGraduation;

   @Column(columnDefinition = "text")
   private String fact_other;

   @Column(columnDefinition = "text")
   private String fact_cls;

   @Column(columnDefinition = "text")
   private String fact_status;


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

 public String getFact_graduation() {
  return fact_graduation;
 }

 public void setFact_graduation(String fact_graduation) {
  this.fact_graduation = fact_graduation;
 }

 public String getFact_postGraduation() {
  return fact_postGraduation;
 }

 public void setFact_postGraduation(String fact_postGraduation) {
  this.fact_postGraduation = fact_postGraduation;
 }

 public String getFact_other() {
  return fact_other;
 }

 public void setFact_other(String fact_other) {
  this.fact_other = fact_other;
 }

 public String getFact_cls() {
  return fact_cls;
 }

 public void setFact_cls(String fact_cls) {
  this.fact_cls = fact_cls;
 }

 public String getFact_status() {
  return fact_status;
 }

 public void setFact_status(String fact_status) {
  this.fact_status = fact_status;
 }
}
