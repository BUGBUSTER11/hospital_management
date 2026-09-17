package com.nt.hospital.service;

import com.nt.hospital.model.MedicalRecord;
import com.nt.hospital.model.Patient;
import com.nt.hospital.repository.MedicalRecordRepository;
import com.nt.hospital.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class MedicalRecordServiceimpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    MedicalRecordServiceimpl(MedicalRecordRepository medicalRecordRepository, PatientRepository patientRepository){
        this.medicalRecordRepository = medicalRecordRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void addMedicalRecord(int id, String diagnosis, String symptoms, String treatment, String notes, Date recordDate,int patient_id) {
       Patient patient = patientRepository.findById(patient_id).orElseThrow(()->new RuntimeException("Patient not found"));

        MedicalRecord medicalRecord = new MedicalRecord(diagnosis,symptoms,treatment,notes,recordDate, patient);
        medicalRecordRepository.save(medicalRecord);
    }


}
