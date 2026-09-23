package com.nt.hospital.service;

import com.nt.hospital.model.Doctor;
import com.nt.hospital.model.User;

public interface DoctorService {

    boolean addDoctor(User user);

    boolean completeDroctor(Doctor doctor);
}
