package com.arkdev.z9tkvtu.service;


import com.arkdev.z9tkvtu.dto.Response.LessonResponse;
import com.arkdev.z9tkvtu.dto.Response.PartResponse;
import com.arkdev.z9tkvtu.dto.Response.SectionDetailsResponse;
import com.arkdev.z9tkvtu.mapper.LessonMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.model.Lesson;
import com.arkdev.z9tkvtu.model.Part;
import com.arkdev.z9tkvtu.repository.LessonRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PracticeService {
    LessonRepository lessonRepository;
    PartRepository partRepository;
    LessonMapper lessonMapper;
    PartMapper partMapper;

    public SectionDetailsResponse getSectionDetails(Integer sectionId) {
        List<LessonResponse> lessons = lessonRepository.findBySectionIdOrderByOrderNumber(sectionId)
                .stream()
                .map(lessonMapper::toLessonResponse)
                .toList();
        List<PartResponse> parts = partRepository.findBySectionsIdOrderByOrderNumber(sectionId)
                .stream()
                .map(partMapper::toPartResponse)
                .toList();
        return new SectionDetailsResponse(
                lessons,
                parts
        );
    }
}
