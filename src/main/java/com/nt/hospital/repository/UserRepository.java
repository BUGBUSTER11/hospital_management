package com.nt.hospital.repository;

import com.nt.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailAndPasswordAndRoleAndStatus(
            String email,
            String password,
            String role,
            String status
    );


    boolean existsByEmail(String email);



    List<User> findByRoleAndStatus(
            String role,
            String status
    );

    List<User> findByRole(String role);
}