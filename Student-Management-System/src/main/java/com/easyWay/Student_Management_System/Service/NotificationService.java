package com.easyWay.Student_Management_System.Service;

import com.easyWay.Student_Management_System.Dto.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {

    String saveNotification(NotificationDto notificationDto);

    String editNotification(NotificationDto notificationDto);

    String deleteNotification(UUID id);

    List<NotificationDto> getAllNotification();



}