package com.nt.hospital.repository;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
