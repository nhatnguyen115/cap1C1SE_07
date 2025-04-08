package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.Response.PartResponse;
import com.arkdev.z9tkvtu.model.Part;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Integer> {
    List<Part> findBySectionsIdOrderByOrderNumber(Integer sectionId);
    List<Part> findByExamsIdOrderByOrderNumber(Integer examId);
    Optional<Part> findByPartNameAndSectionsId(@NotNull(message = "Part name must be not null") String partName, Integer sectionId);

    Optional<Part> findByPartNameAndExamsId(@NotNull(message = "Part name must be not null") String partName, Integer examId);

    @Query("select p from Part p join p.exams e join UserTestAttempt uta on uta.exam = e where uta.id = :attemptId")
    List<Part> findPartsByAttemptId(@Param("attemptId") Integer attemptId);
}