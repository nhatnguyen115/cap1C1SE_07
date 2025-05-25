package com.arkdev.z9tkvtu.dto.response;

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
