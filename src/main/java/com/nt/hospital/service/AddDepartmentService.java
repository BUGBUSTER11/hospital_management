package com.nt.hospital.service;

import com.nt.hospital.model.Department;
import com.nt.hospital.model.User;

import java.util.List;

public interface AddDepartmentService {

    List<User> getDirectors();

    boolean addDepartment(Department department, int directorId);


    long DepartmentCount();


    List<Department> getActiveDepartments();

    List<Department> getInActiveDepartments();

    List<Department> getDepartment();

    Department getDepartmentById(int id);
}