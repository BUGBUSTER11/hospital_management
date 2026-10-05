package com.nt.hospital.service.impl;


import com.nt.hospital.model.Appointment;
import com.nt.hospital.repository.AppointmentRepository;
import com.nt.hospital.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment requestAppointment(Appointment appointment) {
          appointment.setStatus("pending");
        appointment.setCreatedAt(LocalDateTime.now());


        return appointmentRepository.save(appointment);
    }
}
