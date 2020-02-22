package com.vaadin.demo.controllers;

import com.vaadin.demo.controllers.dto.JournalEntryDTO;
import com.vaadin.demo.controllers.dto.PatientDTO;
import com.vaadin.demo.entities.JournalEntry;
import com.vaadin.demo.entities.Patient;
import com.vaadin.demo.repositories.DoctorRepository;
import com.vaadin.demo.repositories.JournalEntryRepository;
import com.vaadin.demo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    PatientRepository patientsRepository;
    @Autowired
    JournalEntryRepository journalEntryRepository;
    @Autowired
    DoctorRepository doctorsRepository;


    @RequestMapping(method = RequestMethod.GET)
    Page<PatientDTO> getPatients(Pageable pageable){
        Page<Patient> result = patientsRepository.findAll(pageable);
        List<PatientDTO> dtos = result.getContent().stream().map(PatientDTO::new).collect(Collectors.toList());
        return new PageImpl<>(dtos, pageable, result.getTotalElements());
    }

    @RequestMapping(method = RequestMethod.PUT)
    ResponseEntity updatePatient(@RequestBody Patient patient){
        patientsRepository.save(patient);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    PatientDTO getPatient(@PathVariable("id") long id){
        return new PatientDTO(patientsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    PatientDTO updatePatient(@PathVariable("id") long id, @RequestBody PatientDTO updated) {

        Patient patient = patientsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        patient.setFirstName(updated.getFirstName());
        patient.setMiddleName(updated.getMiddleName());
        patient.setLastName(updated.getLastName());
        patient.setBirthDate(updated.getBirthDate());
        patient.setSsn(updated.getSsn());
        patient.setGender(updated.getGender());
        patient.setDoctor(doctorsRepository.findById(updated.getDoctor().getId())
                .orElseThrow(IllegalArgumentException::new));
        return new PatientDTO(patientsRepository.save(patient));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity deletePatient(@PathVariable("id") long id){
        patientsRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(path = "/{id}/journalentries", method = RequestMethod.GET)
    Set<JournalEntryDTO> getJournalEntries(@PathVariable("id") long id){
        return patientsRepository
                .findById(id).orElseThrow(IllegalArgumentException::new)
                .getJournalEntries()
                .stream()
                .map(JournalEntryDTO::new)
                .collect(Collectors.toSet());
    }

    @RequestMapping(path = "/{id}/journalentries", method = RequestMethod.PUT)
    ResponseEntity addJournalEntry(@PathVariable("id") long id, @RequestBody JournalEntry newEntry){
        Patient patient = patientsRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);
        patient.getJournalEntries().add(newEntry);
        patientsRepository.save(patient);
        return ResponseEntity.ok().build();
    }
}
