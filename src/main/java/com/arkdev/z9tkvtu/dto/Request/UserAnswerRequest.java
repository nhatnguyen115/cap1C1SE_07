package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.model.Question;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserAnswer}
 */
@AllArgsConstructor
@Getter
public class UserAnswerRequest implements Serializable {

    @NotNull(message = "Question id must be not null")
    private Integer questionId;

    @NotNull(message = "Selected answer must be not null")
    private String selectedAnswer;
}