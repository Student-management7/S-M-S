package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HotelCheckInEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface HotelCheckInnRepo extends JpaRepository<HotelCheckInEntity, UUID> {

    @Query("select g from HotelCheckInEntity g where g.hotelCode = :code")
    List<HotelCheckInEntity> getCheckinn(String code);
}
