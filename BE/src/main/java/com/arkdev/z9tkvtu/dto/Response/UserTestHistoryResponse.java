package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserTestAttempt}
 */
public record UserTestHistoryResponse(
        Integer  id,
        String examName,
        Timestamp startTime,
        Timestamp endTime,
        Integer totalScore) implements Serializable {
}