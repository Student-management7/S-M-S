package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.Document;
import com.easyWay.Student_Management_System.Entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document, UUID> {

    @Query("SELECT g FROM Document g where g.schoolCode = :code")
    List<Document> getAll(@Param("code") String code);
}
