package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttendanceInfoRepo extends JpaRepository<StudentAttendance,UUID>{
}