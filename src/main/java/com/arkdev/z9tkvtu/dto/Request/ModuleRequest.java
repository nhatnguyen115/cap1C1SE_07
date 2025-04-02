package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.ModuleType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Module}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ModuleRequest implements Serializable {
    @NotNull(message = "Module type must be not null")
    ModuleType moduleType;
}