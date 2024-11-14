package com.easyWay.Student_Management_System.Controller;

import com.easyWay.Student_Management_System.Dto.NotificationDto;
import com.easyWay.Student_Management_System.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/save")
    public String saveNotification(@RequestBody NotificationDto details) {
        return notificationService.saveNotification(details);
    }

    @PostMapping("/edit")
    public String editNotification(@RequestBody NotificationDto details) {
        return notificationService.editNotification(details);
    }

    @PostMapping("/delete")
    public String deleteNotification(@RequestParam UUID id) {
        return notificationService.deleteNotification(id);
    }

    @GetMapping("/getAllNotification")
    public List<NotificationDto> getAllNotification() {
        return notificationService.getAllNotification();
    }

}