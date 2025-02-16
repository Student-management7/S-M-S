package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class permissions {

    public StudentPermissionsDto Student;
    public FinancePermissionsDto finance;
    public FacultyPermissionsDto faculty;
    public NotificationPermissionDto Notification;
    public SubjectPermissionDto Subject;

}
