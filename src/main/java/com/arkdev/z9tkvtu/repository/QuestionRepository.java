package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByPartIdOrderByOrderNumber(Integer partId);
    Integer findMaxOrderNumberByPartId(Integer partId);
}