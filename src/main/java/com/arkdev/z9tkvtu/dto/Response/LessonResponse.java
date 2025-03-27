package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.Content;

import java.io.Serializable;

/**
 * DTO for {@link com.arkdev.z9tkvtu.model.Lesson}
 */
public record LessonResponse(String lessonName,
                             Content contentType,
                             String articleText,
                             Integer duration,
                             Integer orderNumber) implements Serializable {
}