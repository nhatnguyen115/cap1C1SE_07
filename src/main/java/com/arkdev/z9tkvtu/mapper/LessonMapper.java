package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.LessonRequest;
import com.arkdev.z9tkvtu.dto.Response.LessonDetailsResponse;
import com.arkdev.z9tkvtu.model.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {
    public Lesson toLesson(LessonRequest request) {
        if (request == null) return null;
        Lesson lesson = new Lesson();
        lesson.setLessonName(request.getLessonName());
        lesson.setContentType(request.getContentType());
        lesson.setArticleText(request.getArticleText());
        lesson.setDuration(request.getDuration());
        return lesson;
    }

    public void updateLesson(Lesson lesson, LessonRequest request) {
        if (request == null) return;
        lesson.setLessonName(request.getLessonName());
        lesson.setContentType(request.getContentType());
        lesson.setArticleText(request.getArticleText());
        lesson.setDuration(request.getDuration());
    }

    public LessonDetailsResponse toLessonResponse(Lesson lesson) {
        if (lesson == null) return null;
        return new LessonDetailsResponse(
                lesson.getLessonName(),
                lesson.getContentType(),
                lesson.getArticleText(),
                lesson.getDuration()
        );
    }
}
