package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.AdminFeesStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AdminFeesRepo extends JpaRepository<AdminFeesStructure , UUID> {

    @Query("select a from AdminFeesStructure a where a.className = :cls and schoolCode = :schoolCode")
    AdminFeesStructure findByClass(String cls, @Param("schoolCode") String schoolCode);

}
