package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.request.LessonRequest;
import com.arkdev.z9tkvtu.dto.request.MediaRequest;
import com.arkdev.z9tkvtu.dto.response.LessonDetailsResponse;
import com.arkdev.z9tkvtu.mapper.LessonMapper;
import com.arkdev.z9tkvtu.model.Lesson;
import com.arkdev.z9tkvtu.model.Media;
import com.arkdev.z9tkvtu.model.Section;
import com.arkdev.z9tkvtu.repository.LessonRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LessonService {
    LessonRepository lessonRepository;
    SectionRepository sectionRepository;
    UploadMediaService uploadMediaService;
    LessonMapper lessonMapper;

    public List<LessonDetailsResponse> getLessons(Integer sectionId) {
        return lessonRepository.findBySectionIdOrderByOrderNumber(sectionId)
                .stream()
                .map(lessonMapper::toLessonDetailsResponse)
                .toList();
    }

    public LessonDetailsResponse getLesson(Integer lessonId) {
        return lessonRepository.findById(lessonId)
                .map(lessonMapper::toLessonDetailsResponse)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
    }

    @Transactional
    public void addLesson(Integer sectionId, LessonRequest request) {
        lessonRepository.findByLessonName(request.getLessonName())
                .ifPresent(lesson -> {
                    throw new RuntimeException("Lesson already exists");
                });
        if (!sectionRepository.existsById(sectionId))
            throw new RuntimeException("Section not found");
        Integer max = lessonRepository.findMaxOrderNumberBySectionId(sectionId);
        Section section = sectionRepository.getReferenceById(sectionId);
        Lesson lesson = lessonMapper.toLesson(request);
        lesson.setOrderNumber(max != null ? max + 1 : 1);
        lesson.setSection(section);
        lessonRepository.save(lesson);
    }

    @Transactional
    public void updateLesson(Integer lessonId, LessonRequest request) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lessonMapper.updateLesson(lesson, request);
    }

    @Transactional
    public void deleteLesson(Integer lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        lessonRepository.delete(lesson);
    }

    @Transactional
    public void addMediaToLesson(Integer lessonId, MediaRequest request) throws IOException {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        Media media = uploadMediaService.updateMedia(lesson.getMedia(),
                request.getFile(), request.getMediaType());
        lesson.setMedia(media);
        lessonRepository.save(lesson);
    }
}
