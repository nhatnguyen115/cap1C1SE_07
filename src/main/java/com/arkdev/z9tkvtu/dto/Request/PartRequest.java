package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.QuestionType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Part}
 */
@AllArgsConstructor
@Getter
public class PartRequest implements Serializable {
    @NotNull(message = "Part name must be not null")
    String partName;

    String description;

    @NotNull(message = "Quest type must be not null")
    QuestionType questionType;

    String instructions;
}