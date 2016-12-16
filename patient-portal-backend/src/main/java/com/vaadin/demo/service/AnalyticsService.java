package com.vaadin.demo.service;

import com.vaadin.demo.entities.Patient;
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

    public List<StringLongPair> getStatsByAge() {
        List<StringLongPair> data = patientsRepository
                .findAll()
                .stream()
                .collect(groupingBy(getAgeRange(), counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    return new StringLongPair(e.getKey(), e.getValue());
                })
                .collect(Collectors.toList());

        return data;
    }

    public List<StringLongPair> getStatsByGender() {
        List<StringLongPair> data = patientsRepository
                .findAll()
                .stream()
                .collect(groupingBy(Patient::getGender, counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    return new StringLongPair(e.getKey().toString(), e.getValue());
                })
                .collect(Collectors.toList());

        return data;
    }

    public List<StringLongPair> getStatsByDoctor() {
        List<StringLongPair> data = patientsRepository
                .findAll()
                .stream()
                .collect(groupingBy(Patient::getDoctor, counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    return new StringLongPair(e.getKey().toString(), e.getValue());
                })
                .collect(Collectors.toList());
        return data;
    }

    private Function<Patient, String> getAgeRange() {
        return p -> {
            int age = getAge(p.getBirthDate());
            String ageRange = "";
            if (age < 21) {
                ageRange = "Under 21";
            } else if (age <= 30) {
                ageRange = "21-30";
            } else if (age <= 40) {
                ageRange = "31-40";
            } else if (age <= 50) {
                ageRange = "41-50";
            } else if (age <= 60) {
                ageRange = "51-60";
            } else if (age <= 70) {
                ageRange = "61-70";
            } else if (age <= 80) {
                ageRange = "71-80";
            } else if (age > 80) {
                ageRange = "Over 80";
            }
            return ageRange;
        };
    }

    private int getAge(Date birthDate) {
        LocalDate now = LocalDate.now();
        LocalDate localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(localBirthDate, now).getYears();
    }
    
}