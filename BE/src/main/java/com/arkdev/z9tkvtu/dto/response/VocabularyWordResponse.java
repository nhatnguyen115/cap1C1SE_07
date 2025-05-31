package com.arkdev.z9tkvtu.dto.response;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.VocabularyWord}
 */
public record VocabularyWordResponse(
        String word,
        String meaning) implements Serializable {
}