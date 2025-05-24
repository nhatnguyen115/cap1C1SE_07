package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.Response.ExamListResponse;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.util.TestType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {
    Optional<Exam> findByExamName(@NotNull(message = "Exam name must be not null") String examName);
    @Query(value = """
        SELECT COUNT(DISTINCT uta.user_id)
        FROM user_test_attempt uta
        WHERE uta.exam_id = :examId
        AND uta.complete = true
    """, nativeQuery = true)
    Integer countByUserTestAttempt(@Param("examId") Integer examId);

    List<Exam> findAllByTestTypeOrderByCreatedAtDesc(TestType testType);

    List<Exam> findAllBySectionsIdAndTestTypeOrderByCreatedAtDesc(Integer sectionId, TestType testType);
}