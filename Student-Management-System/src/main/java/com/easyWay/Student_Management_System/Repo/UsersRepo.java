package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users , UUID> {
  Users findUsersByEmail(String email);
}
