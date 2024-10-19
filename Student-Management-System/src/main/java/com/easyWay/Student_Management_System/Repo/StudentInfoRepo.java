package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Dto.StudentInfoDto;
import com.easyWay.Student_Management_System.Entity.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface StudentInfoRepo extends JpaRepository<StudentInfo, UUID> {

    @Query("select p from StudentInfo p where p.cls = :clss")
    List<StudentInfo> findByClass(@Param("clss") String clss);

   @Query("SELECT p FROM StudentInfo p WHERE p.name = :name AND p.cls = :clss")
   List<StudentInfo> findByClassAndName(@Param("clss")String cls,@Param("name") String name);

}
