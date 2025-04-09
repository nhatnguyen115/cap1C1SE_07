package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.DifficultyLevel;

import java.io.Serializable;
import java.util.Map;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Question}
 */
public record QuestionResponse(
        Integer id,
        String content,
        Map<String, Object> options,
        String correctAnswer,
        DifficultyLevel difficulty,
        Integer orderNumber) implements Serializable {
}