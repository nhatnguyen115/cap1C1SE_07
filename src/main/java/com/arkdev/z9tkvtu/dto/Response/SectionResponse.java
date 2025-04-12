package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Section}
 */
public record SectionResponse(
        Integer id,
        String sectionName,
        String description,
        String sectionType) implements Serializable {
}