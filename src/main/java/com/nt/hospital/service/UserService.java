package com.nt.hospital.service;

import com.nt.hospital.model.User;

public interface UserService {
    User loginUser(String email, String password);

}
