package com.arkdev.z9tkvtu.dto.Response;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Role}
 */
public record RoleResponse(
        String roleName,
        String description) implements Serializable {
}