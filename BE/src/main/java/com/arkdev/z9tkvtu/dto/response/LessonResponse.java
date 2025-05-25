package com.arkdev.z9tkvtu.dto.response;

import com.arkdev.z9tkvtu.util.Content;

public record LessonResponse(
        Integer id,
        String lessonName,
        Content contentType,
        String articleText,
        Integer duration) {
}
