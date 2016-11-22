package com.vaadin.demo.repositories;

import com.vaadin.demo.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
}
