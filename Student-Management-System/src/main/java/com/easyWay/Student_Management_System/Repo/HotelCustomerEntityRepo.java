package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HotelCustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotelCustomerEntityRepo extends JpaRepository<HotelCustomersEntity, UUID> {

    @Query("select g from HotelCustomersEntity g where g.adharNo = :aadhar")
    HotelCustomersEntity findByAadhar(String aadhar);

}
