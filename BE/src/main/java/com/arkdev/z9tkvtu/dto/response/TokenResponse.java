package com.arkdev.z9tkvtu.dto.response;

import com.arkdev.z9tkvtu.util.RoleType;

import java.io.Serializable;

public record TokenResponse(
        String token,
        RoleType role) implements Serializable {
}
