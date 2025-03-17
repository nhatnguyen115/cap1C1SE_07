package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Permission}
 */
public record PermissionResponse(
        String permissionName,
        String description) implements Serializable {
}