package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.response.GrammarResponse;
import com.arkdev.z9tkvtu.model.Grammar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrammarRepository extends JpaRepository<Grammar, Integer> {

    List<Grammar> findBySectionId(Integer sectionId);
}