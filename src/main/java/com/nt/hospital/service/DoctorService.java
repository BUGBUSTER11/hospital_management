package com.nt.hospital.service;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;

import java.util.List;

public interface DoctorService {

    boolean addDoctor(User user);

    boolean completeDroctor(Doctor doctor);

    List<Doctor> getAllDoctorsData();

    long getAllDoctorCount();


    Doctor getDoctorByEmail(String email);

    Doctor getDoctorById(int doctorId);

    boolean updateDoctor(Doctor doctor);
}
