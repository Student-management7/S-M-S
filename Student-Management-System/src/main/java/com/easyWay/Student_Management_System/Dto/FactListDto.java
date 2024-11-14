package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FactListDto {

    public UUID factId;
    public String attendance;
    public String name;

}
