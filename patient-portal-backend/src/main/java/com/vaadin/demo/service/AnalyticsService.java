package com.vaadin.demo.service;

import com.vaadin.demo.entities.Doctor;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    PatientRepository patientsRepository;

    public List<HashMap<String, Object>> getStatsByAge() {

        List<HashMap<String, Object>> result = new ArrayList<>();

        patientsRepository.queryGetStatsByAge().forEach(e -> result.add(createMap("age", e[1], e[0])));

        return result;
    }

    public List<HashMap<String, Object>> getStatsByGender() {

        List<HashMap<String, Object>> result = new ArrayList<>();

        patientsRepository.queryGetStatsByGender().forEach(e -> result.add(createMap("gender", e[1], e[0])));

        return result;
    }

    public List<HashMap<String, Object>> getStatsByDoctor() {
        List<HashMap<String, Object>> result = new ArrayList<>();

        patientsRepository.queryGetStatsByDoctor().forEach(e -> {
            Doctor d = (Doctor) e[1];
            result.add(createMap("doctor", d.getLastName() + ", " + d.getFirstName(), e[0]));
        });

        return result;

    }

    private HashMap<String, Object> createMap(String groupingType, Object label, Object patientCount) {

        HashMap<String, Object> map = new HashMap();

        map.put(groupingType, label);

        map.put("patients", patientCount);

        return map;
    }


}
