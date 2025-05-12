package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public record AttemptDetailsResponse(
        String examName,
        Integer examScore,
        Integer userScore,
        Timestamp startTime,
        Timestamp endTime,
        List<PartDetailsResponse<?>> details)implements Serializable {
}
