package com.nt.hospital.model;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class MedicalRecord {

    @Id
    private int id;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String notes;
    private LocalDate recordDate;

    public MedicalRecord(){}

    public MedicalRecord(String diagnosis, String treatment, String symptoms, LocalDate recordDate, String notes, int id) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.notes = notes;
        this.recordDate = recordDate;
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

    public void setRecordDate(LocalDate recordDate) {
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

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public String getNotes() {
        return notes;
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
