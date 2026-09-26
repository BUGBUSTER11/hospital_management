package com.nt.hospital.repository;

import com.nt.hospital.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddDepartmentRepository extends JpaRepository<Department, String> {
}
