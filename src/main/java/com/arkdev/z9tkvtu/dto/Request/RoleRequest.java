package com.arkdev.z9tkvtu.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Role}
 */
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest implements Serializable {
    @NotNull(message = "Role name must be not null")
    @Size(max = 10)
    String roleName;

    @NotNull(message = "Description must be not null")
    String description;
}