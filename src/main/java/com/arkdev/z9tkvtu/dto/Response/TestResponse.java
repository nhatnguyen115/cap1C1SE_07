package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.TestType;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Test}
 */
public record TestResponse(
        Integer id,
        TestType testType) implements Serializable {
}