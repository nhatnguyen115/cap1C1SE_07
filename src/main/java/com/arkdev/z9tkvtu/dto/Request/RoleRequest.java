package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.RoleType;
import jakarta.validation.constraints.NotNull;
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
    RoleType roleType;

    String description;
}