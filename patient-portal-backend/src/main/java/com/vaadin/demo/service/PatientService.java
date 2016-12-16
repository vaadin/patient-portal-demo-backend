package com.vaadin.demo.service;

import com.vaadin.demo.entities.Patient;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            p = repo.findOne(p.getId());
            p.getJournalEntries().size();
        }
        return p;
    }

}
