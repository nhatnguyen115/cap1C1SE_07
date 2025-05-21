package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.sql.Timestamp;

public record ExamDetailsResponse(
        Integer id,
        String examName,
        Integer totalScore,
        Integer duration,
        Integer userScore,
        Timestamp startTime,
        Timestamp endTime) implements Serializable {
}
