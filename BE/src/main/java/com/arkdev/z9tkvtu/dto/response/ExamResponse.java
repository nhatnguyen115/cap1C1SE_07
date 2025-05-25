package com.arkdev.z9tkvtu.dto.response;

import com.arkdev.z9tkvtu.util.DifficultyLevel;

import java.io.Serializable;

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