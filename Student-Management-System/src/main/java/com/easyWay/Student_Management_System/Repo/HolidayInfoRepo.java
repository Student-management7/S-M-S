package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HolidayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HolidayInfoRepo extends JpaRepository<HolidayInfo, UUID> {

    @Query("select a from HolidayInfo a where a.schoolCode = :code")
    List<HolidayInfo> findAll(@Param("code") String code);

}
