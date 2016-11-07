package com.vaadin.demo.controllers.dto;


import com.vaadin.demo.entities.Gender;
import com.vaadin.demo.entities.Patient;

import java.util.Date;

public class PatientDTO {

    private Long id;
    private String title;
    private Long medicalRecord;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender gender;
    private Date birthDate;
    private Date lastEntry;
    private Date lastVisit;
    private String ssn;
    private String pictureUrl;
    private DoctorDTO doctor;

    public PatientDTO(Patient patient) {
        this.id = patient.getId();
        this.title = patient.getTitle();
        this.medicalRecord = patient.getMedicalRecord();
        this.firstName = patient.getFirstName();
        this.middleName = patient.getMiddleName();
        this.lastName = patient.getLastName();
        this.gender = patient.getGender();
        this.birthDate = patient.getBirthDate();
        this.ssn = patient.getSsn();
        this.pictureUrl = patient.getPictureUrl();
        this.lastVisit = patient.getLastVisit();
        this.doctor = new DoctorDTO(patient.getDoctor());
        this.lastEntry = patient.getLastVisit();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getMedicalRecord() {
        return medicalRecord;
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

    public Date getLastEntry() {
        return lastEntry;
    }

    public void setLastEntry(Date lastEntry) {
        this.lastEntry = lastEntry;
    }

    public DoctorDTO getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorDTO doctor) {
        this.doctor = doctor;
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
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }
}
