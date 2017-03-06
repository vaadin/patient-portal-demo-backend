package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT COUNT(p), '21-30' AS c FROM Patient p WHERE (p.birthDate >= '1987-01-01' AND p.birthDate <= '1997-01-01') UNION " +
            "SELECT COUNT(p), '31-40' AS c FROM Patient p WHERE (p.birthDate >= '1977-01-01' AND p.birthDate <= '1987-01-01') UNION " +
            "SELECT COUNT(p2), '41-50' AS c  FROM Patient p2 WHERE p2.birthDate >= '1967-01-01' AND p2.birthDate <= '1977-01-01' UNION " +
            "SELECT COUNT(p3), '51-60' AS c  FROM Patient p3 WHERE p3.birthDate >= '1957-01-01' AND p3.birthDate <= '1967-01-01' UNION " +
            "SELECT COUNT(p4), '61-70' AS c  FROM Patient p4 WHERE p4.birthDate >= '1947-01-01' AND p4.birthDate <= '1957-01-01' UNION " +
            "SELECT COUNT(p5), '71-80' AS c  FROM Patient p5 WHERE p5.birthDate >= '1937-01-01' AND p5.birthDate <= '1947-01-01'")
    List<Object[]> queryGetStatsByAge();

    @Query("SELECT COUNT(p), p.gender FROM Patient p GROUP BY p.gender")
    List<Object[]> queryGetStatsByGender();

    @Query("SELECT COUNT(p), p.doctor FROM Patient p GROUP BY p.doctor ")
    List<Object[]> queryGetStatsByDoctor();
}
