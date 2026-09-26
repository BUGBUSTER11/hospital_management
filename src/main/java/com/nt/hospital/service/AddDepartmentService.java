package com.nt.hospital.service;

import com.nt.hospital.model.Department;
import com.nt.hospital.model.User;

import java.util.List;

public interface AddDepartmentService {

    List<User> getDirectors();

    Department addDepartment(Department department, int directorId);

}