package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacultyInfoRepo extends JpaRepository<FacultyInfo , UUID> {
}
