package com.nt.hospital.service.impl;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.repository.DoctorRepository;
import com.nt.hospital.repository.UserRepositry;
import com.nt.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserRepositry userRepositry;

    @Override
    public boolean addDoctor(User user) {

        if (userRepositry.existsByEmail(user.getEmail())) {
            return false;
        }



        userRepositry.save(user);

        return true;

    }

    @Override
    public boolean completeDroctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return true;
    }
}
