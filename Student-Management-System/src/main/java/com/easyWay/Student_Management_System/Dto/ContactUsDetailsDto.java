package com.easyWay.Student_Management_System.Dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.UUID;

@Data
public class ContactUsDetailsDto {
    public UUID id;
    public String username;
    public String email;
    public String contact;

    @Column(columnDefinition = "Text")
    public String remarks;
}
