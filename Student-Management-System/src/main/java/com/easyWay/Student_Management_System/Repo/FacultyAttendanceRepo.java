package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultyAttendance;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FacultyAttendanceRepo extends JpaRepository<FacultyAttendance, UUID> {

    @Query("select a from FacultyAttendance a where a.creationDateTime Between :from AND :to ")
    FacultyAttendance findByTime(@Param("from") LocalDateTime from,
                                 @Param("to") LocalDateTime to);

    @Query("SELECT f FROM FacultyAttendance f WHERE f.creationDateTime BETWEEN :fromDate AND :toDate")
    List<FacultyAttendance> findAllByTimeBetween(LocalDateTime fromDate, LocalDateTime toDate);

}
