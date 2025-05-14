package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;

public record PracticeDetailsResponse<T>(
    T exam,
    List<PartAttemptResponse<?, ?>> details) implements Serializable {
}
