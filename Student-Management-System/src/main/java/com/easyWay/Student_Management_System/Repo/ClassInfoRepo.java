package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.CLassInfo;
import com.easyWay.Student_Management_System.Entity.FacultyInfo;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassInfoRepo extends JpaRepository<CLassInfo, UUID> {

    @Query("select v from CLassInfo v where v.schoolCode = :code ")
    List<CLassInfo> getBySchoolName(@Param("code") String name);

    @Query("SELECT p FROM CLassInfo p WHERE p.className = :clss and p.schoolCode = :code")
    CLassInfo findByClass(@Param("clss") String clss, @Param("code") String code);

    @Query("select p from CLassInfo p where  p.schoolCode = :code and p.id = :id")
    CLassInfo getById(@Param("code") String code, @Param("id") UUID id);


}
