package com.vaadin.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor {

    @Id @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Patient> patients;

    public Doctor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Doctor() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
