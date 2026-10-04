package com.nt.hospital.repository;

import com.nt.hospital.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddDepartmentRepository extends JpaRepository<Department, String> {

    List<Department> findByStatus(String status);
}
