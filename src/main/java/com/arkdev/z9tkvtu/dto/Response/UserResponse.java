package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.Gender;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserLoginData}
 */
public record UserResponse(
        UUID id,
        String firstName,
        String lastName,
        Gender gender,
        Date dob,
        String email,
        String phoneNumber) implements Serializable {
}