package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

import java.util.UUID;

@Data
public class DocumentDto {
    public String title;
    public String cls;
    public String subject;
    public boolean publish;
    public String name;
    public UUID id;

}
