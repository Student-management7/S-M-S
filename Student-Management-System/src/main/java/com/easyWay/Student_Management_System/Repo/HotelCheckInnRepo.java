package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HotelCheckinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public interface HotelCheckInnRepo extends JpaRepository<HotelCheckinEntity, UUID> {

    @Query("select g from HotelCheckinEntity g where g.hotelCode = :code")
    ArrayList<HotelCheckinEntity> getCheckinn(String code);
}
