package com.nt.hospital.repository;

import com.nt.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositry extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPasswordAndRoleAndStatus(String email, String password, String role, String status);

    boolean existsByEmail(String email);

   Optional<User>  findByEmailAndRole(String email, String doctor);
}