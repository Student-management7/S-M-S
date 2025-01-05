package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class Permissions {

    public StudentPermissionsDto Student;
    public FinancePermissionsDto finance;
    public FacultyPersmissionsDto faculty;
    public NotificationPermissionDto Notification;
    public SubjectPermissionDto Subject;

}
