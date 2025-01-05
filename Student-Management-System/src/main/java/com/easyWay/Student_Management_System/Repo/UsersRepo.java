package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users , UUID> {
  Users findUsersByEmail(String email);

  @Query("select u from Users u where u.email = :email Or u.schoolCode = :schCode")
  Users findUsersByEmailOrSchoolCode(@Param("email") String email, @Param("schCode") String schCode);

}
