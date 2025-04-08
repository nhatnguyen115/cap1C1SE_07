package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;

public record AnswerResponse(
        Integer questionId,
        String selectedAnswer,
        String correctAnswer,
        Integer orderNumber,
        boolean isCorrect) implements Serializable {
}
