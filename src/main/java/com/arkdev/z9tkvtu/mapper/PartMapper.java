package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.PartRequest;
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
        return part;
    }

    public void updatePart(Part part, PartRequest request) {
        if(request == null) return;
        part.setPartName(request.getPartName());
        part.setQuestionType(request.getQuestionType());
        part.setDescription(request.getDescription());
        part.setInstructions(request.getInstructions());
    }

    public PartResponse toPartResponse(Part part) {
        if(part == null) return null;
        return new PartResponse(
                part.getPartName(),
                part.getDescription(),
                part.getQuestionType(),
                part.getInstructions()
        );
    }
}
