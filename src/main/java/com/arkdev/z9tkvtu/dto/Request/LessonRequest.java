package com.arkdev.z9tkvtu.dto.Request;

import com.arkdev.z9tkvtu.util.Content;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Lesson}
 */
@AllArgsConstructor
@Getter
public class LessonRequest implements Serializable {
    @NotNull(message = "Lesson name must be not null")
    String lessonName;

    @NotNull(message = "Content type must be not null")
    Content contentType;

    String articleText;

    Integer duration;

    @NotNull(message = "Order number must be not null")
    Integer orderNumber;
}