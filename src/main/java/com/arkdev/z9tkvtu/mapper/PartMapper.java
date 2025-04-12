package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.PartRequest;
import com.arkdev.z9tkvtu.dto.Response.PartDetailsResponse;
import com.arkdev.z9tkvtu.dto.Response.PartResponse;
import com.arkdev.z9tkvtu.model.Part;
import org.springframework.stereotype.Component;

@Component
public class PartMapper {
    public Part toPart(PartRequest request) {
        if(request == null) return null;
        Part part = new Part();
        part.setPartName(request.getPartName());
        part.setQuestionType(request.getQuestionType());
        part.setDescription(request.getDescription());
        part.setInstructions(request.getInstructions());
        part.setQuestionCount(request.getQuestionCount());
        return part;
    }

    public void updatePart(Part part, PartRequest request) {
        if(request == null) return;
        part.setPartName(request.getPartName());
        part.setQuestionType(request.getQuestionType());
        part.setDescription(request.getDescription());
        part.setInstructions(request.getInstructions());
        part.setQuestionCount(request.getQuestionCount());
    }

    public PartDetailsResponse toPartDetailsResponse(Part part) {
        if(part == null) return null;
        return new PartDetailsResponse(
                part.getId(),
                part.getPartName(),
                part.getDescription(),
                part.getQuestionType(),
                part.getInstructions(),
                part.getQuestionCount()
        );
    }

    public PartResponse toPartResponse(Part part) {
        if(part == null) return null;
        return new PartResponse(
                part.getId(),
                part.getPartName(),
                part.getQuestionType(),
                part.getQuestionCount()
        );
    }
}
