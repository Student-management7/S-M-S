package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FileTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FileTrackingRepo extends JpaRepository<FileTracking , UUID> {
}