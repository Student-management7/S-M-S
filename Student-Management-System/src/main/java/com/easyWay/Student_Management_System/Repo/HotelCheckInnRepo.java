package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HotelCheckInEntity;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface HotelCheckInnRepo extends JpaRepository<HotelCheckInEntity, UUID> {

    @Query("select g from HotelCheckInEntity g where g.hotelCode = :code")
    List<HotelCheckInEntity> getCheckinn(String code);


    @Query("SELECT g FROM HotelCheckInEntity g " +
            "WHERE g.hotelCode = :code " +
            "AND g.arrivalDate BETWEEN :fromDate AND :toDate")
    List<HotelCheckInEntity> getCheckinnByDate(
            @Param("code") String code,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );
}
