package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class NotificationDto {

    public UUID id;
    public String startDate;
    public String endDate;
    public String description;
    public String cato;
    public List<String> className;
}