package com.nt.hospital.service.impl;

import com.nt.hospital.model.Department;
import com.nt.hospital.model.User;
import com.nt.hospital.repository.AddDepartmentRepository;
import com.nt.hospital.repository.UserRepository;
import com.nt.hospital.service.AddDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddDepartmentServiceImpl implements AddDepartmentService {

    @Autowired
    private AddDepartmentRepository addDepartmentRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getDirectors() {

        return userRepository.findByRoleAndStatus(
                "DIRECTOR",
                "ACTIVE"
        );
    }


    @Override
    public Department addDepartment(
            Department department,
            int directorId) {

        User director = userRepository.findById(directorId)
                .orElseThrow(() ->
                        new RuntimeException("Director not found")
                );


        // Backend validation
        if (!"DIRECTOR".equalsIgnoreCase(director.getRole())) {

            throw new RuntimeException(
                    "Selected user is not a Director"
            );
        }


        // Assign selected Director to Department
        department.setDirector(director);


        // Save Department
        return addDepartmentRepository.save(department);
    }
}