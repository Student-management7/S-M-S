package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.ReportCardEntity;
import com.easyWay.Student_Management_System.Entity.SchoolCreationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ReportCardRepo extends JpaRepository<ReportCardEntity , UUID> {

    @Query("select a from ReportCardEntity a where a.schoolCode = :code and a.id = :id")
    ReportCardEntity getById(@Param("code") String code, @Param("id") UUID id);

    @Query("select a from ReportCardEntity a where a.schoolCode = :code")
    List<ReportCardEntity> findAll(@Param("code") String code);

    @Query("SELECT a FROM ReportCardEntity a WHERE a.schoolCode = :code AND a.studentInfo.id = :studentInfoId")
    List<ReportCardEntity> findBySchoolCodeAndStudentInfoId(@Param("code") String schoolCode, @Param("studentInfoId") UUID studentInfoId);


}
