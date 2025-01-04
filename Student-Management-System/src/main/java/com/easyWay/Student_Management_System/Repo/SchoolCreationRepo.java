package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SchoolCreationRepo extends JpaRepository<SchoolCreationEntity , UUID> {

    @Query("select a from SchoolCreationEntity a where a.schoolCode = :code and a.id = :id")
    SchoolCreationEntity getById(@Param("code") String code, @Param("id") UUID id);
}
