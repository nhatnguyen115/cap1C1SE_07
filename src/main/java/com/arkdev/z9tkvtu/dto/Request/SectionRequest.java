package com.arkdev.z9tkvtu.dto.Request;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Section}
 */
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionRequest implements Serializable {
    @NotNull(message = "Section name must be not null")
    String sectionName;
    
    String description;
    
    @NotNull(message = "Order number must be not null")
    Integer orderNumber;
}