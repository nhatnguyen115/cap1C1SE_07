package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;

public record ResultPartResponse(
        String partName,
        Integer orderNumber,
        List<AnswerResponse> answerResponses) implements Serializable {
}
