package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Exam;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
    Optional<Exam> findByExamName(@NotNull(message = "Exam name must be not null") String examName);

    List<Exam> findByTestIdOrderByExamName(Integer testId);
}