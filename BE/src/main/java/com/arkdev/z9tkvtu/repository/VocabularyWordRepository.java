package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.response.VocabularyWordResponse;
import com.arkdev.z9tkvtu.model.VocabularyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VocabularyWordRepository extends JpaRepository<VocabularyWord, Integer> {
    List<VocabularyWord> findAllBySectionId(Integer sectionId);
}