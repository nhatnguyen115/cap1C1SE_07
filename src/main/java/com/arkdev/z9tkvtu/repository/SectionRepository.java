package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Integer> {
    Optional<Section> findBySectionName(String sectionName);
}