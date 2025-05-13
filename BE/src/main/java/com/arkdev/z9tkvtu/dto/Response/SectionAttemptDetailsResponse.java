package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;

public record SectionAttemptDetailsResponse(
    Integer totalQuestions,
    Integer totalTime,
    Integer correctCount,
    Integer incorrectCount,
    List<PartDetailsResponse<?>> details)implements Serializable {
}
