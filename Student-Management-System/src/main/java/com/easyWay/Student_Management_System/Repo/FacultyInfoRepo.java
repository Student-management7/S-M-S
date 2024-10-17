package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FacultyInfoRepo extends JpaRepository<FacultyInfo , UUID> {
}
