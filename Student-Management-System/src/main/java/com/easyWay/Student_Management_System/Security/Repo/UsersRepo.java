package com.easyWay.Student_Management_System.Security.Repo;

import com.easyWay.Student_Management_System.Security.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepo extends JpaRepository<Users, UUID> {
    Users findByEmail(String email);
}
