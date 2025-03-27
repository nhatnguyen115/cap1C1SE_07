package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.PartRequest;
import com.arkdev.z9tkvtu.dto.Response.PartResponse;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.model.Part;
import com.arkdev.z9tkvtu.model.Section;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
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
    PartMapper partMapper;

    public List<PartResponse> getParts(Integer sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new RuntimeException("section not found"))
                .getParts().stream().map(partMapper::toPartResponse).toList();
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
}
