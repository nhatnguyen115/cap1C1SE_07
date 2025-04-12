package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.QuestionType;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Part}
 */
public record PartResponse(
        Integer id,
        String partName,
        String description,
        QuestionType questionType,
        String instructions,
        Integer questionCount) implements Serializable {
}