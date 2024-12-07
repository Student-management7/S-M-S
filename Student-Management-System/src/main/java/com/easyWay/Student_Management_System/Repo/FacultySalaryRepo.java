package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultySalaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FacultySalaryRepo extends JpaRepository<FacultySalaryEntity, UUID> {
}
