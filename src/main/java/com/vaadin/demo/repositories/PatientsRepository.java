package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
}
