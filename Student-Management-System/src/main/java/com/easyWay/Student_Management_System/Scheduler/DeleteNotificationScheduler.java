package com.easyWay.Student_Management_System.Scheduler;

import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import com.easyWay.Student_Management_System.Repo.NotificationRepo;
import com.easyWay.Student_Management_System.Security.ClaimService;
import com.easyWay.Student_Management_System.Utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class DeleteNotificationScheduler {

    @Autowired
    NotificationRepo repo;

    @Autowired
    ClaimService claimService;

   @Scheduled(cron = "59 59 23 * * *")
    public void performNightlyTask() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDateTime dateAndTime = TimeUtils.toEndOfDay(date.format(formatter));
        List<NotificationEntity> data = repo.getByEndDate(String.valueOf(dateAndTime), claimService.getLoggedInUserSchoolCode());
        log.info("List of notifications {}", data);

        for(NotificationEntity entity:data){
            repo.deleteById(entity.getId());
        }
    }
}
