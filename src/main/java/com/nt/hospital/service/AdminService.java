package com.nt.hospital.service;

import com.nt.hospital.model.User;

public interface AdminService {

    User loginUser(String email, String password);
}