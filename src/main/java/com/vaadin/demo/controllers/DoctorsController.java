package com.vaadin.demo.controllers;

import com.vaadin.demo.controllers.dto.DoctorDTO;
import com.vaadin.demo.controllers.dto.PatientDTO;
import com.vaadin.demo.repositories.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorsController {

    @Autowired
    DoctorsRepository doctorsRepository;

    @RequestMapping(method = RequestMethod.GET)
    Collection<DoctorDTO> getPatients() {
        return doctorsRepository
                .findAll()
                .stream()
                .map(DoctorDTO::new)
                .collect(Collectors.toSet());
    }
}
