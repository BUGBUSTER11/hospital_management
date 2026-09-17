package com.nt.hospital.service;

import java.sql.Date;

public interface MedicalRecordService {

    public void addMedicalRecord(int id, String diagnosis, String symptoms, String treatment, String notes, Date recordDate, int patient_id);


}
