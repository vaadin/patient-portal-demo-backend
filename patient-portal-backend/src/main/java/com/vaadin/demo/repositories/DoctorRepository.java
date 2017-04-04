package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Doctor;
import com.vaadin.demo.service.StringLongPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
