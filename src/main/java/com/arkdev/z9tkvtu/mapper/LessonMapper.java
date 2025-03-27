package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.LessonRequest;
import com.arkdev.z9tkvtu.dto.Response.LessonResponse;
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
        lesson.setOrderNumber(request.getOrderNumber());
        return lesson;
    }

    public void updateLesson(Lesson lesson, LessonRequest request) {
        if (request == null) return;
        lesson.setLessonName(request.getLessonName());
        lesson.setContentType(request.getContentType());
        lesson.setArticleText(request.getArticleText());
        lesson.setDuration(request.getDuration());
        lesson.setOrderNumber(request.getOrderNumber());
    }

    public LessonResponse toLessonResponse(Lesson lesson) {
        if (lesson == null) return null;
        return new LessonResponse(
                lesson.getLessonName(),
                lesson.getContentType(),
                lesson.getArticleText(),
                lesson.getDuration(),
                lesson.getOrderNumber()
        );
    }
}
