package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.CLassInfo;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClassInfoRepo extends JpaRepository<CLassInfo, UUID> {

    @Query("select v from CLassInfo v where v.schoolName = :name ")
    List<CLassInfo> getBySchoolName(String name);
}
