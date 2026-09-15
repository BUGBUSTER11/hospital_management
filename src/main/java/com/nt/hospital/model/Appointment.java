package com.nt.hospital.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    private int appointmentId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String status;

    private String reason;

    private String notes;

    private LocalDateTime createdAt;


    public Appointment() {
    }

    public Appointment(LocalDate appointmentDate,
                       LocalTime appointmentTime,
                       String status,
                       String reason,
                       String notes,
                       LocalDateTime createdAt
                     ) {

        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
        this.reason = reason;
        this.notes = notes;
        this.createdAt = createdAt;

    }


    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId
                + ", appointmentDate=" + appointmentDate
                + ", appointmentTime=" + appointmentTime
                + ", status=" + status
                + ", reason=" + reason
                + ", notes=" + notes
                + ", createdAt=" + createdAt + "]";
    }
}