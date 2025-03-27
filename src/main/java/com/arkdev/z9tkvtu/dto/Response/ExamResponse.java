package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Exam}
 */
public record ExamResponse(
        String examName,
        Short totalScore,
        Short duration) implements Serializable {
}