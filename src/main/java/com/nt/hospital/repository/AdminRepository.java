package com.nt.hospital.repository;

import com.nt.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPasswordAndRoleAndStatus(String email, String password, String role, String status);
}

//
//INSERT INTO user
//        (name, email, password, contact, role, status)
//VALUES
//        ('Admin', 'admin@gmail.com', 'admin123', 9876543210, 'ADMIN', 'ACTIVE');