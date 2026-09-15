package com.nt.hospital.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Doctor {

    @Id
    private int id;

    private String doctorName;
    private String doctorGender;
    private int doctorAge;
    private String specialization;
    private long doctorNumber;


    public Doctor(){

    }

    public Doctor(String doctorName, String doctorGender, int doctorAge, String specialization, long doctorNumber) {
        this.doctorName = doctorName;
        this.doctorGender = doctorGender;
        this.doctorAge = doctorAge;
        this.specialization = specialization;
        this.doctorNumber = doctorNumber;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorGender() {
        return doctorGender;
    }

    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }

    public int getDoctorAge() {
        return doctorAge;
    }

    public void setDoctorAge(int doctorAge) {
        this.doctorAge = doctorAge;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public long getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(long doctorNumber) {
        this.doctorNumber = doctorNumber;
    }



    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", doctorGender='" + doctorGender + '\'' +
                ", doctorAge=" + doctorAge +
                ", specialization='" + specialization + '\'' +
                ", doctorNumber=" + doctorNumber +
                '}';
    }
}

