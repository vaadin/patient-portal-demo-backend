package com.vaadin.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "journalEntry")
public class JournalEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 10000)
    private String entry;
    @NotNull
    private AppointmentType appointmentType;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;

    public JournalEntry(Date date, String entry, AppointmentType appointmentType) {
        this.date = date;
        this.entry = entry;
        this.appointmentType = appointmentType;
    }

    public JournalEntry() {
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
