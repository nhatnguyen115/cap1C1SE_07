package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.UUID;

public record UserRankResponse(
        String username,
        Integer totalScore,
        Integer totalTime) implements Serializable {
}
