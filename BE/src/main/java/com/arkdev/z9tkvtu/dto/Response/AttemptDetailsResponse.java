package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;

public record AttemptDetailsResponse(
        String examName,
        Integer examScore,
        Integer userScore,
        Integer userTime,
        List<PartDetailsResponse<?>> details)implements Serializable {
}
