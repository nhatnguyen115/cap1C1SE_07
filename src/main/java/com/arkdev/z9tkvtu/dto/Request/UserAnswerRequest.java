package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.model.Question;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.UserAnswer}
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerRequest implements Serializable {

    @NotNull(message = "Question id must be not null")
    private Integer questionId;

    @NotNull(message = "Selected answer must be not null")
    private String selectedAnswer;
}