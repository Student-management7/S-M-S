package com.easyWay.Student_Management_System.Dto;

import lombok.Data;

@Data
public class NotificationPermissionDto {
    public boolean createNotification;
    public boolean notificationList;
    public boolean holidayFormController;
    public boolean notificationController;
}
