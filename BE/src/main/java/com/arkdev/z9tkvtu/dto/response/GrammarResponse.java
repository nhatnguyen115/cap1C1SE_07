package com.arkdev.z9tkvtu.dto.response;

import com.arkdev.z9tkvtu.util.MediaType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Grammar}
 */
public record GrammarResponse(
        String grammarName,
        String grammarText,
        MediaType mediaType,
        String url) implements Serializable {
}