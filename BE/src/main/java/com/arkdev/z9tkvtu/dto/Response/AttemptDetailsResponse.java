package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;

public record AttemptDetailsResponse(
        ExamResponse exam,
        List<PartDetailsResponse<?>> details)implements Serializable {
}
