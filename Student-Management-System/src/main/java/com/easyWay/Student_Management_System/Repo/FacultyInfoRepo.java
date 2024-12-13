package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FacultyInfoRepo extends JpaRepository<FacultyInfo , UUID> {

    @Query("select a from FacultyInfo a where a.isDelete = false and p.schoolCode = :code")
    List<FacultyInfo> findAllFaculty(@Param("code") String code);

    @Query("select p from FacultyInfo p where p.isDelete = false and p.schoolCode = :code and p.id = :id")
    FacultyInfo getById(@Param("code") String code, @Param("id") UUID id);

}
