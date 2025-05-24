package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserTestAttempt}
 */
public record TestAttemptResponse(
        Integer id,
        Integer totalScore,
        Integer listeningScore,
        Integer readingScore,
        Integer correctCount,
        Integer incorrectCount,
        Integer skipCount,
        Timestamp startTime,
        Timestamp endTime) implements Serializable {

}