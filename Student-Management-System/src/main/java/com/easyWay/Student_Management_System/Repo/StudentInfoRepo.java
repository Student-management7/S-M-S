package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentInfoRepo extends JpaRepository<StudentInfo, UUID> {
}
