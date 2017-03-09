package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(value = "select tuples.\"range\", count(ageList.age) as \"number of occurences\"\n" +
            "  from " +
            "  ( " +
            "     select 0 min, 9 max, '0-9' \"range\"\n" +
            "     union all\n" +
            "     select 10, 19, '10-19'\n" +
            "     union all\n" +
            "     select 20, 29, '20-29'\n" +
            "     union all\n" +
            "     select 30, 39, '30-39'\n" +
            "     union all\n" +
            "     select 40, 49, '40-49'\n" +
            "     union all\n" +
            "     select 50, 59, '50-59'\n" +
            "     union all\n" +
            "     select 60, 69, '60-69'\n" +
            "     union all \n " +
            "     select 70, 79, '70-79'\n" +
            "     union all\n" +
            "     select 80, 89, '80-89'\n" +
            "     union all\n" +
            "     select 90, 99, '90-99'\n" +
            "  ) as tuples\n" +
            "  left join (SELECT EXTRACT(years from age(CURRENT_DATE , CAST(p.birthDate AS DATE))) as age FROM Patient p) AS ageList\n" +
            "    on ageList.age between tuples.min and tuples.max \n" +
            " group by tuples.range ORDER BY tuples.range", nativeQuery = true)
    List<Object[]> queryGetStatsByAge();

    @Query("SELECT COUNT(p), p.gender FROM Patient p GROUP BY p.gender")
    List<Object[]> queryGetStatsByGender();

    @Query("SELECT COUNT(p), p.doctor FROM Patient p GROUP BY p.doctor ")
    List<Object[]> queryGetStatsByDoctor();

}
