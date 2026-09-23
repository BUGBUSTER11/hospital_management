package com.nt.hospital.repository;

import com.nt.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<User, Integer> {

}

//
//INSERT INTO user
//        (name, email, password, contact, role, status)
//VALUES
//        ('Admin', 'admin@gmail.com', 'admin123', 9876543210, 'ADMIN', 'ACTIVE');