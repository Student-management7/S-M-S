package com.easyWay.Student_Management_System.Repo;

import com.easyWay.Student_Management_System.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UsersRepo extends JpaRepository<Users , UUID> {
  Users findUsersByEmail(String email);

  @Query("select g from Users g where g.schoolCode is null")
  List<Users> getAdminDetail();

  @Query("select g from Users g where g.email = :email and g.schoolCode  = :code")
  Users findUsersByEmail(String email, String code);

  @Query("select g from Users g where g.schoolCode = :code")
  List<Users> getAllDetail(String code);
}
