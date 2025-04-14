package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public record UserResultResponse(
        String examName,
        int totalScore,
        List<PartAnswer> parts
) implements Serializable {
    public record PartAnswer(
            String partName,
            List<AnswerResponse> answers
    ) implements Serializable {
    }
    public record AnswerResponse(
            String content,
            Map<String, String> options,
            String explanation,
            String correctAnswer,
            String selectedAnswer
    ) implements Serializable {
    }
}

