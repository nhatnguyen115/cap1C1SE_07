package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.DifficultyLevel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Exam}
 */
public record ExamResponse(
        Integer id,
        String examName,
        Integer totalScore,
        Integer duration,
        Integer questionCount,
        DifficultyLevel level) implements Serializable {
}