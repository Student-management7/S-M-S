package com.easyWay.Student_Management_System.Scheduler;

import com.easyWay.Student_Management_System.Utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DeleteNotificationScheduler {


    //@Scheduled(cron = "59 59 23 * * *")
    @Scheduled(cron = "* * * * * *")
    public void performNightlyTask() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime dateAndTime = TimeUtils.toEndOfDay(date.format(formatter));
        System.out.println(dateAndTime);
    }
}
