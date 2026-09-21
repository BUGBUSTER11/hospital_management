package com.nt.hospital.service.impl;

import com.nt.hospital.model.User;
import com.nt.hospital.repository.AdminRepository;
import com.nt.hospital.repository.UserRepositry;
import com.nt.hospital.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositry userRepositry;

    @Override
    public User loginUser(String email, String password) {

        Optional<User> isPresentUser = userRepositry.findByEmailAndPasswordAndRoleAndStatus(email, password, "ADMIN", "ACTIVE");

        if (isPresentUser.isPresent()) {

            return isPresentUser.get();
        }

        return null;
    }
}
