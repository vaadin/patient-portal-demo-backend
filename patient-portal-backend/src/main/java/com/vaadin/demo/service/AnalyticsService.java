package com.vaadin.demo.service;

import com.vaadin.demo.entities.Doctor;
import com.vaadin.demo.entities.Patient;
import com.vaadin.demo.repositories.DoctorRepository;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import org.springframework.stereotype.Service;

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
