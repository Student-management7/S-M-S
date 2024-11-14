package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepo extends JpaRepository<NotificationEntity, UUID> {

}