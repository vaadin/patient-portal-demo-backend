package com.vaadin.demo.entities;

import java.util.ArrayList;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Patient extends Person {

    private Long medicalRecord;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<JournalEntry> journalEntries = new ArrayList<>();

    @Temporal(jakarta.persistence.TemporalType.TIMESTAMP)
    private Date lastVisit;

    public Patient() {
    }

    public Long getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(Long medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    @PrePersist
    void prePersist() {
        if (getJournalEntries() != null && !getJournalEntries().isEmpty()) {
            lastVisit = getJournalEntries().get(getJournalEntries().size() - 1).getDate();
        }
    }

}
