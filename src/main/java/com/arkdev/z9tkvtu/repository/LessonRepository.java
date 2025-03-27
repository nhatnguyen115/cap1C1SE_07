package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Lesson;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    Optional<Lesson> findByLessonName(@NotNull(message = "Lesson name must be not null") String lessonName);
}