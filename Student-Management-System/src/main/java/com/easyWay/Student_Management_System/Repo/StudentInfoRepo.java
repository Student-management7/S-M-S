package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.UUID;

public interface StudentInfoRepo extends JpaRepository<StudentInfo, UUID> {

    @Query("select p from StudentInfo p where p.isDelete = false and p.schoolCode = :code and p.id = :id")
    StudentInfo getById(@Param("code") String code, @Param("id") UUID id);

    @Query("select p from StudentInfo p where p.cls = :clss and p.isDelete = false and p.schoolCode = :code")
    List<StudentInfo> findByClass(@Param("clss") String clss, @Param("code") String code);

    @Query("select p from StudentInfo p where p.cls = :clss and p.isDelete = false and p.name= :name and p.schoolCode = :code")
    List<StudentInfo> findByClassAndName(@Param("clss") String clss ,@Param("name") String name, @Param("code") String code);

    @Query("select p from StudentInfo p where p.name = :name and p.isDelete = false and p.schoolCode = :code")
    List<StudentInfo> findByName(@Param("name") String name, @Param("code") String code);

    @Query("select p from StudentInfo p where p.isDelete = false and p.schoolCode = :code")
    List<StudentInfo> findAllStudent(@Param("code") String code);


}
