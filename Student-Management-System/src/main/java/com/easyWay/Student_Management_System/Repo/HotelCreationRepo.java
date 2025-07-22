package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.HotelCreationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HotelCreationRepo extends JpaRepository<HotelCreationEntity, UUID> {


}
