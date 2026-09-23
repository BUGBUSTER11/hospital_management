package com.nt.hospital.service;

import com.nt.hospital.model.User;

public interface UserService {
    User loginUser(String email, String password);

    User getUserIdAndRoleByEmail(String email);
}
