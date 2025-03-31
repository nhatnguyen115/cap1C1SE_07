package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}