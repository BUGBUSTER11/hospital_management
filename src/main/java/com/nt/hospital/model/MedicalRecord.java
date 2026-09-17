package com.nt.hospital.model;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String notes;
    private Date recordDate;
    @ManyToOne
    @JoinColumn(name = "patiend_id")
    private Patient patient;

    public MedicalRecord(){}

    public MedicalRecord(String diagnosis, String symptoms, String treatment, String notes, Date recordDate, Patient patient) {
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.notes = notes;
        this.recordDate = recordDate;
        this.patient = patient;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public int getId() {
        return id;
    }

    public String getTreatment() {
        return treatment;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public String getNotes() {
        return notes;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "diagnosis='" + diagnosis + '\'' +
                ", id=" + id +
                ", symptoms='" + symptoms + '\'' +
                ", treatment='" + treatment + '\'' +
                ", notes='" + notes + '\'' +
                ", recordDate=" + recordDate +
                '}';
    }
}
