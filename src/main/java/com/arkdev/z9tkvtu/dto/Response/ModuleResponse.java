package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.ModuleType;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Module}
 */
public record ModuleResponse(
        ModuleType moduleType
) implements Serializable {
}