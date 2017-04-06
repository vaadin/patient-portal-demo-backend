package com.vaadin.demo.controllers;

import com.vaadin.demo.controllers.dto.DoctorDTO;
import com.vaadin.demo.controllers.dto.PatientDTO;
import com.vaadin.demo.repositories.PatientRepository;
import com.vaadin.demo.service.AnalyticsService;
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
    AnalyticsService analyticsService;

    @RequestMapping(path = "/age", method = RequestMethod.GET)
    public Map<String, Object> getStatsByAge() {
        List<HashMap<String, Object>> data = analyticsService
                .getStatsByAgeGroup()
                .stream()
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("age", e.getGroup());
                    stats.put("patients", e.getCount());
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
        List<HashMap<String, Object>> data = analyticsService
                .getStatsByGender()
                .stream()
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("gender", e.getGroup());
                    stats.put("patients", e.getCount());
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
        List<HashMap<String, Object>> data = analyticsService
                .getStatsByDoctor()
                .entrySet()
                .stream()
                .filter(e -> e.getKey() != null)
                .map(e -> {
                    HashMap<String, Object> stats = new HashMap<>();
                    stats.put("doctor", new DoctorDTO(e.getKey()));
                    stats.put("patients", e.getValue());
                    return stats;
                })
                .collect(Collectors.toList());

        HashMap<String, Object> result = new HashMap<>();
        result.put("grouping", "doctor");
        result.put("data", data);
        return result;
    }

}