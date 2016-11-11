package com.vaadin.demo.controllers;

import com.vaadin.demo.controllers.dto.JournalEntryDTO;
import com.vaadin.demo.controllers.dto.PatientDTO;
import com.vaadin.demo.repositories.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientsController {

    @Autowired
    PatientsRepository patientsRepository;

    @RequestMapping(method = RequestMethod.GET)
    Collection<PatientDTO> getPatients(){
        return patientsRepository
                .findAll()
                .stream()
                .map(PatientDTO::new)
                .collect(Collectors.toSet());
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    PatientDTO getPatient(@PathVariable("id") long id){
        return new PatientDTO(patientsRepository.findOne(id));
    }

    @RequestMapping(path = "/{id}/journalentries", method = RequestMethod.GET)
    Set<JournalEntryDTO> getJournalEntries(@PathVariable("id") long id){
        return patientsRepository
                .findOne(id)
                .getJournalEntries()
                .stream()
                .map(JournalEntryDTO::new)
                .collect(Collectors.toSet());
    }

    @RequestMapping(path = "/{id}/journalentries", method = RequestMethod.PUT)
    ResponseEntity<?> addJournalEntry(@PathVariable("id") long id, @RequestBody JournalEntryDTO newEntry){
//
//        Patient patient = patientsRepository
//                .findOne(id);
//        JournalEntry journalEntry = new JournalEntry(newEntry.getDate(), newEntry.getEntry(), newEntry.getAppointmentType());
//        journalEntry.setDoctor(patient.getDoctor());
//
//        patient.getJournalEntries().add(journalEntry);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location).build();
        return ResponseEntity.ok().build();
    }
}
