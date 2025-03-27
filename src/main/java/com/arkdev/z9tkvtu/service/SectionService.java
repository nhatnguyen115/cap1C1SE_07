package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.SectionRequest;
import com.arkdev.z9tkvtu.dto.Response.SectionResponse;
import com.arkdev.z9tkvtu.mapper.SectionMapper;
import com.arkdev.z9tkvtu.model.Module;
import com.arkdev.z9tkvtu.model.Section;
import com.arkdev.z9tkvtu.repository.ModuleRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SectionService {
    SectionRepository sectionRepository;
    ModuleRepository moduleRepository;
    SectionMapper sectionMapper;

    public List<SectionResponse> getSections() {
        return sectionRepository.findAll()
                .stream()
                .map(sectionMapper::toSectionResponse)
                .toList();
    }

    public SectionResponse getSection(Integer sectionId) {
        return sectionRepository.findById(sectionId)
                .map(sectionMapper::toSectionResponse)
                .orElseThrow(() -> new RuntimeException("Section not found"));
    }

    public void addSection(Integer moduleId, SectionRequest request) {
        Section section = sectionRepository.findBySectionName(request.getSectionName())
                .orElse(null);
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module not found"));
        if (section != null)
            throw new RuntimeException("Section already exists");
        section = sectionMapper.toSection(request);
        module.getSections().add(section);
        moduleRepository.save(module);
    }

    public void updateSection(Integer sectionId, SectionRequest request) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        sectionMapper.updateSection(section, request);
        sectionRepository.save(section);
    }

    public void deleteSection(Integer sectionId) {
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("Section not found"));
        sectionRepository.delete(section);
    }
}
