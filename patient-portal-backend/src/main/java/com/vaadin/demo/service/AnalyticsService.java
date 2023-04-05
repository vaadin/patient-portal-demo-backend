package com.vaadin.demo.service;

import com.vaadin.demo.entities.Doctor;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    @Autowired
    PatientRepository patientsRepository;

    public List<StringLongPair> getStatsByAgeGroup() {
        return patientsRepository.getStatsByAgeGroup();
    }

    public List<StringLongPair> getStatsByGender() {
        return patientsRepository.getStatsByGender();
    }

    public Map<Doctor,Long> getStatsByDoctor() {
        return patientsRepository.getStatsByDoctor();
    }

}
