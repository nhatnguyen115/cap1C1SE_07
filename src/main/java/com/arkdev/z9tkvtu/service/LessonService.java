package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.LessonRequest;
import com.arkdev.z9tkvtu.dto.Response.LessonResponse;
import com.arkdev.z9tkvtu.mapper.LessonMapper;
import com.arkdev.z9tkvtu.model.Lesson;
import com.arkdev.z9tkvtu.model.Section;
import com.arkdev.z9tkvtu.repository.LessonRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonService {
    LessonRepository lessonRepository;
    SectionRepository sectionRepository;
    LessonMapper lessonMapper;

    public List<LessonResponse> getLessons() {
        return lessonRepository.findAll()
                .stream()
                .map(lessonMapper::toLessonResponse)
                .toList();
    }

    public LessonResponse getLesson(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lessonMapper::toLessonResponse)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    public void addLesson(Integer sectionId, LessonRequest request) {
        Lesson lesson = lessonRepository.findByLessonName(request.getLessonName())
                .orElse(null);
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        if (lesson != null)
            throw new RuntimeException("Lesson already exists");
        lesson = lessonMapper.toLesson(request);
        section.getLessons().add(lesson);
        sectionRepository.save(section);
    }

    public void updateLesson(Integer lessonId, LessonRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lessonMapper.updateLesson(lesson, request);
        lessonRepository.save(lesson);
    }

    public void deleteLesson(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lessonRepository.delete(lesson);
    }
}
