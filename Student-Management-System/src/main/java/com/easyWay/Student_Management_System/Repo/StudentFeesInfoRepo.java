package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.StudentFeeInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentFeesInfoRepo extends JpaRepository<StudentFeeInfo, UUID> {
}
