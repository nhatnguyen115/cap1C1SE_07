package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.QuestionType;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Part}
 */
public record PartDetailsResponse<T>(
        PartResponse part,
        List<T> list) implements Serializable {
}