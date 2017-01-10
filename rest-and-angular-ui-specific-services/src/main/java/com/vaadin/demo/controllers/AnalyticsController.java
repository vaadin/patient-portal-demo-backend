package com.vaadin.demo.controllers;

import com.vaadin.demo.controllers.dto.PatientDTO;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    PatientRepository patientsRepository;

    @RequestMapping(path = "/age", method = RequestMethod.GET)
    public Map<String, Object> getStatsByAge() {
        List<HashMap<String, Object>> data = patientsRepository
                .findAll()
                .stream()
                .map(PatientDTO::new)
                .collect(groupingBy(getAgeRange(), counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("age", e.getKey());
                    stats.put("patients", e.getValue());
                    return stats;
                })
                .collect(Collectors.toList());

        HashMap<String, Object> result = new HashMap<>();
        result.put("grouping", "age");
        result.put("data", data);
        return result;
    }

    @RequestMapping(path = "/gender", method = RequestMethod.GET)
    public Map<String, Object> getStatsByGender() {
        List<HashMap<String, Object>> data = patientsRepository
                .findAll()
                .stream()
                .map(PatientDTO::new)
                .collect(groupingBy(PatientDTO::getGender, counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("gender", e.getKey());
                    stats.put("patients", e.getValue());
                    return stats;
                })
                .collect(Collectors.toList());

        HashMap<String, Object> result = new HashMap<>();
        result.put("grouping", "gender");
        result.put("data", data);
        return result;
    }

    @RequestMapping(path = "/doctor", method = RequestMethod.GET)
    public Map<String, Object> getStatsByDoctor() {
        List<HashMap<String, Object>> data = patientsRepository
                .findAll()
                .stream()
                .map(PatientDTO::new)
                .collect(groupingBy(PatientDTO::getDoctor, counting()))
                .entrySet()
                .stream()
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("doctor", e.getKey());
                    stats.put("patients", e.getValue());
                    return stats;
                })
                .collect(Collectors.toList());

        HashMap<String, Object> result = new HashMap<>();
        result.put("grouping", "doctor");
        result.put("data", data);
        return result;
    }

    private Function<PatientDTO, String> getAgeRange() {
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
        LocalDate localBirthDate = new Date(birthDate.getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return Period.between(localBirthDate, now).getYears();
    }
}