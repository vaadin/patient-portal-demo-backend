package com.vaadin.demo.controllers.dto;

import com.vaadin.demo.entities.AppointmentType;
import com.vaadin.demo.entities.JournalEntry;

import java.util.Date;

public class JournalEntryDTO {

    private Date date;
    private DoctorDTO doctor;
    private String entry;
    private AppointmentType appointmentType;

    public JournalEntryDTO(JournalEntry entry) {
        this.date = entry.getDate();
        this.doctor = new DoctorDTO(entry.getDoctor());
        this.entry = entry.getEntry();
        this.appointmentType = entry.getAppointmentType();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
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


}
