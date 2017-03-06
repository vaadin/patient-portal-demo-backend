package com.vaadin.demo.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "doctors")
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


