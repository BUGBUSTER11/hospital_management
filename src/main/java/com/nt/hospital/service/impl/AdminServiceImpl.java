package com.nt.hospital.service.impl;

import com.nt.hospital.model.User;
import com.nt.hospital.repository.AdminRepository;
import com.nt.hospital.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public User loginUser(String email, String password) {

        Optional<User> isPresentUser =
                adminRepository.findByEmailAndPasswordAndRoleAndStatus(
                        email,
                        password,
                        "ADMIN",
                        "ACTIVE"
                );

        if (isPresentUser.isPresent()) {

            return isPresentUser.get();
        }

        return null;
    }
}