package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.TestType;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Test}
 */
@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TestRequest implements Serializable {
    @NotNull(message = "Test type must be not null")
    TestType testType;
}