package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.DifficultyLevel;
import com.arkdev.z9tkvtu.util.MediaType;

import java.util.Map;

public record UserAnswerResponse(
        String content,
        String url,
        MediaType mediaType,
        Map<String, Object> options,
        String correctAnswer,
        String explanation,
        DifficultyLevel difficulty,
        String selectedAnswer
) {
}
