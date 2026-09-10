package com.nt.hospital.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Prescription {

    @Id
    private int id;
    private String medicineName;
    private String dosage;
    private String frequency;
    private String duration;
    private String instructions;
    private LocalDate date;

    public Prescription(){}

    public Prescription(LocalDate date, String dosage, String duration, String frequency, int id, String instructions, String medicineName) {
        this.date = date;
        this.dosage = dosage;
        this.duration = duration;
        this.frequency = frequency;
        this.id = id;
        this.instructions = instructions;
        this.medicineName = medicineName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
