package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.sql.Timestamp;

public record ExamDetailsResponse(
        Integer id,
        String examName,
        Integer duration,
        Integer totalScore,
        Integer listeningScore,
        Integer readingScore,
        Integer correctCount,
        Integer incorrectCount,
        Integer skipCount,
        Timestamp startTime,
        Timestamp endTime) implements Serializable {
}
