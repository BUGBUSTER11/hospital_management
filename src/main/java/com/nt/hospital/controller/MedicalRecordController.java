package com.nt.hospital.controller;

import com.nt.hospital.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MedicalRecordController {

    @Autowired
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping("/addMedicalRecord")
    public void addMedicalRecord(@RequestParam int id, @RequestParam String diagnosis, @RequestParam String symptoms, @RequestParam String treatment, @RequestParam String notes, @RequestParam Date recordDate,@RequestParam int patient_id)
    {
        medicalRecordService.addMedicalRecord(id,diagnosis,symptoms,treatment,notes,recordDate,patient_id);
    }
}
