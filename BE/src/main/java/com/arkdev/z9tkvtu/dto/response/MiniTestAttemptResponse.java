package com.arkdev.z9tkvtu.dto.Response;

import java.sql.Timestamp;

public record MiniTestAttemptResponse(
        Integer id,
        Integer totalScore,
        Integer correctCount,
        Integer incorrectCount,
        Integer skipCount,
        Timestamp startTime,
        Timestamp endTime
) {
}
