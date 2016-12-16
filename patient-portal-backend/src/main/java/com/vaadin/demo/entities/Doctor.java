package com.vaadin.demo.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doctor extends Person {
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Patient> patients;

    public Doctor(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Doctor() {
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
