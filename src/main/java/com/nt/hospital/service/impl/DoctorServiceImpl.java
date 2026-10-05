package com.nt.hospital.service.impl;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;
import com.nt.hospital.repository.DoctorRepository;
import com.nt.hospital.repository.UserRepositry;
import com.nt.hospital.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
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

    @Override
    public List<Doctor> getAllDoctorsData() {
        return doctorRepository.findAll();
    }

    @Override
    public long getAllDoctorCount() {
       return doctorRepository.count();
    }

    @Override
    public Doctor getDoctorByEmail(String email) {

        return doctorRepository
                .findByUser_Email(email)
                .orElse(null);
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
     Optional<Doctor> doctor =  doctorRepository.findById(doctorId);
     if (doctor.isPresent()){
         return doctor.get();
     }
        return null;
    }

    @Override
    public boolean updateDoctor(Doctor doctor) {

        User user = doctor.getUser();
        userRepositry.save(user);

        doctorRepository.save(doctor);
        return true;

    }
}
