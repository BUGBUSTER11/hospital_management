package com.nt.hospital.service.impl;

import com.nt.hospital.model.Department;
import com.nt.hospital.repository.DepartmentRepository;
import com.nt.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

@Autowired
DepartmentRepository departmentRepository;
    @Override
    public List<Department> getAllDepartmets() {
        return departmentRepository.findAll();
    }
}
