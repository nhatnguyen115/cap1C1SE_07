package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.Gender;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserLoginData}
 */
public record UserResponse(
        String firstName,
        String lastName,
        Gender gender,
        Date dob,
        String email,
        String phoneNumber) implements Serializable {
}