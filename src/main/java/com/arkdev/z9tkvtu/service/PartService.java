package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.MediaRequest;
import com.arkdev.z9tkvtu.dto.Request.PartRequest;
import com.arkdev.z9tkvtu.dto.Response.PartResponse;
import com.arkdev.z9tkvtu.mapper.MediaMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PartService {
    SectionRepository sectionRepository;
    PartRepository partRepository;
    ExamRepository examRepository;
    MediaMapper mediaMapper;
    PartMapper partMapper;

    public List<PartResponse> getPartsToSection(Integer sectionId) {
        return partRepository.findBySectionsIdOrderByOrderNumber(sectionId)
                .stream()
                .map(partMapper::toPartResponse)
                .toList();
    }

    public List<PartResponse> getPartsToExam(Integer examId) {
        return partRepository.findByExamsIdOrderByOrderNumber(examId)
                .stream()
                .map(partMapper::toPartResponse)
                .toList();
    }

    public PartResponse getPart(Integer partId) {
        return partRepository.findById(partId)
                .map(partMapper::toPartResponse)
                .orElseThrow(() -> new RuntimeException("part not found"));
    }

    @Transactional
    public void addPartToSection(Integer sectionId, PartRequest request) {
        partRepository.findByPartNameAndSectionsId(request.getPartName(), sectionId)
            .ifPresent(part -> {
                throw new RuntimeException("Part already exists");
            });
        Section section = sectionRepository.getReferenceById(sectionId);
        Part part = partMapper.toPart(request);
        part.getSections().add(section);
        section.getParts().add(part);
        partRepository.save(part);
    }

    @Transactional
    public void addPartToExam(Integer examId, PartRequest request) {
        partRepository.findByPartNameAndExamsId(request.getPartName(), examId)
                .ifPresent(part -> {
                    throw new RuntimeException("Part already exists");
                });
        Exam exam = examRepository.getReferenceById(examId);
        Part part = partMapper.toPart(request);
        part.getExams().add(exam);
        exam.getParts().add(part);
        partRepository.save(part);
    }

    @Transactional
    public void updatePart(Integer partId, PartRequest request) {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("part not found"));
        partMapper.updatePart(part, request);
        partRepository.save(part);
    }

    @Transactional
    public void deletePart(Integer partId) {
        Part part= partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("part not found"));
        partRepository.delete(part);
    }

    @Transactional
    public void addMediaToPart(Integer partId, MediaRequest request) {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        Media media = mediaMapper.toMedia(request);
        part.setMedia(media);
        partRepository.save(part);
    }
}
