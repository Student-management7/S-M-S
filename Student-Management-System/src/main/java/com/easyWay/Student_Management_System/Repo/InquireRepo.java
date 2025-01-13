package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.InquireEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InquireRepo extends JpaRepository<InquireEntity , UUID> {
}
