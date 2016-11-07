package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorsRepository extends JpaRepository<Doctor, Long> {
}
