package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class permissions {

    public StudentPermissionsDto student;
    public FinancePermissionsDto finance;
    public FacultyPermissionsDto faculty;
    public NotificationPermissionDto notification;
    public SubjectPermissionDto subject;
    public SyllabusDto syllabus;
    public TcDto tc;

}
