package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FileTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface FileTrackingRepo extends JpaRepository<FileTracking , UUID> {

    @Query("select a from FileTracking a where a.schoolCode = :code")
    List<FileTracking> getFileTracking(String code);
}
