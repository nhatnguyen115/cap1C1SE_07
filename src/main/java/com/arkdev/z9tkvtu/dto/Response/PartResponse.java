package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.QuestionType;

public record PartResponse(
        Integer partId,
        String partName,
        QuestionType questionType,
        Integer questionCount,
        String description,
        String instructions
        ) {
}
