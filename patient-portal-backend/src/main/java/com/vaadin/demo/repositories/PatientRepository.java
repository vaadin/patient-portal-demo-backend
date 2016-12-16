package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
