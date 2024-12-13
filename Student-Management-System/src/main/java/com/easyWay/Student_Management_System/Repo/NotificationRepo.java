package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface NotificationRepo extends JpaRepository<NotificationEntity, UUID> {

    @Query("SELECT g FROM NotificationEntity g where g.endDate = :time and g.schoolCode = :code")
    List<NotificationEntity> getByEndDate(String time, @Param("code") String code);

    @Query("SELECT g FROM NotificationEntity g where g.schoolCode = :code")
    List<NotificationEntity> getAll(@Param("code") String code);


}