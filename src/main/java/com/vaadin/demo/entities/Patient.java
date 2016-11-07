package com.vaadin.demo.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Patient {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Long medicalRecord;
    private String firstName;
    private String middleName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Date birthDate;
    private String ssn;
    private String pictureUrl;

    @ManyToOne
    private Doctor doctor;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<JournalEntry> journalEntries;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public Long getMedicalRecord() {
        return medicalRecord;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMedicalRecord(Long medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    public void setJournalEntries(Set<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public Date getLastVisit() {
        return this.getJournalEntries()
                .stream()
                .map(JournalEntry::getDate)
                .max(Date::compareTo).orElse(null);
    }

    public Date getLastEntry() {
        return this.getLastVisit();
    }

}
