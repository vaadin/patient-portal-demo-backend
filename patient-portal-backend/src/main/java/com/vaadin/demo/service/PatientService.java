package com.vaadin.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vaadin.demo.entities.Patient;
import com.vaadin.demo.repositories.PatientRepository;

/**
 * @author mstahv
 */
@Service
@Transactional
public class PatientService {

    @Autowired
    PatientRepository repo;

    public Patient findAttached(Patient p) {
        if (p.isPersistent()) {
            Optional<Patient> patient = repo.findById(p.getId());
            if (patient.isPresent()) {
                patient.get().getJournalEntries().size();
                return patient.get();
            }
        }
        return p;
    }

}
