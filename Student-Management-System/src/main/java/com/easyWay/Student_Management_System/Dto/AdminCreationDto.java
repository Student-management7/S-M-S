package com.easyWay.Student_Management_System.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public class AdminCreationDto {

    @JsonProperty("id")
    public UUID Id;
    private String name;
    private String role;
    private String email;
    private String password;
    private List<String> assignedSchools;

    // Constructors


    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAssignedSchools() {
        return assignedSchools;
    }

    public void setAssignedSchools(List<String> assignedSchools) {
        this.assignedSchools = assignedSchools;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    // toString method
    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", assignedSchools=" + assignedSchools +
                '}';
    }
}
