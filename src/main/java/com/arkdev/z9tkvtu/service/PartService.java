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
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
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
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("section not found"))
                .getParts().stream()
                .sorted(Comparator.comparing(Part::getOrderNumber))
                .map(partMapper::toPartResponse)
                .toList();
    }

    public List<PartResponse> getPartsToExam(Integer examId) {
        return examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("exam not found"))
                .getParts().stream()
                .sorted(Comparator.comparing(Part::getOrderNumber))
                .map(partMapper::toPartResponse)
                .toList();
    }

    public PartResponse getPart(Integer partId) {
        return partRepository.findById(partId)
                .map(partMapper::toPartResponse)
                .orElseThrow(() -> new RuntimeException("part not found"));
    }

    public void addPartToSection(Integer sectionId, PartRequest request) {
        Part part = partRepository.findByPartName(request.getPartName())
                .orElse(null);
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("section not found"));
        if(part != null)
            throw new RuntimeException("part already exists");
        part = partMapper.toPart(request);
        section.getParts().add(part);
        sectionRepository.save(section);
    }

    public void addPartToExam(Integer examId, PartRequest request) {
        Part part = partRepository.findByPartName(request.getPartName())
                .orElse(null);
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("exam not found"));
        if(part != null)
            throw new RuntimeException("part already exists");
        part = partMapper.toPart(request);
        exam.getParts().add(part);
        examRepository.save(exam);
    }

    public void updatePart(Integer partId, PartRequest request) {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("part not found"));
        partMapper.updatePart(part, request);
        partRepository.save(part);
    }

    public void deletePart(Integer partId) {
        Part part= partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("part not found"));
        partRepository.delete(part);
    }

    public void addMediaToPart(Integer partId, MediaRequest request) {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        Media media = mediaMapper.toMedia(request);
        part.setMedia(media);
        partRepository.save(part);
    }
}
