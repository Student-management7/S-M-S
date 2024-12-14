package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import java.util.List;
import java.util.UUID;
@Repository
public interface AttendanceInfoRepo extends JpaRepository<StudentAttendance,UUID>{

    @Query("SELECT c FROM StudentAttendance c " +
            "WHERE c.className = :cls " +
            "AND c.subject = :subject " +
            "AND c.creationDateTime BETWEEN :fromDate AND :toDate and c.schoolCode = :code")
    List<StudentAttendance> findByClassAndSubject(
            @Param("cls") String cls,
            @Param("subject") String subject,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            @Param("code") String code);

    @Query("SELECT c FROM StudentAttendance c " +
            "WHERE c.className = :cls " +
            "AND c.creationDateTime BETWEEN :fromDate AND :toDate " +
            "AND c.schoolCode = :code " +
            "AND (c.subject IS NULL OR TRIM(c.subject) = '')")
    List<StudentAttendance> findByClassAndSubjectMaster(
            @Param("cls") String cls,
            @Param("fromDate") LocalDateTime fromDate,
            @Param("toDate") LocalDateTime toDate,
            @Param("code") String code);

}