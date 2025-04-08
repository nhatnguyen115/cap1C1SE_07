package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.DifficultyLevel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.Map;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Question}
 */
@AllArgsConstructor
@Getter
public class QuestionRequest implements Serializable {
    @NotNull(message = "Content must be not null")
    String content;

    @NotNull(message = "Options must be not null")
    Map<String, Object> options;

    @NotNull(message = "Correct answer must be not null")
    String correctAnswer;

    String explanation;

    DifficultyLevel difficulty;

    @NotNull(message = "Order Number must be not null")
    Integer orderNumber;
}