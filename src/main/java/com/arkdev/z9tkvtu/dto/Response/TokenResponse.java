package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;

public record TokenResponse(
        String accessToken,
        String refreshToken) implements Serializable {
}
