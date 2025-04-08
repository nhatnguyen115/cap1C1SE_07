package com.arkdev.z9tkvtu.dto.Response;

import java.io.Serializable;
import java.util.List;


public record UserTestDetailsResponse(
        Integer attemptId,
        List<ResultPartResponse> resultPartResponses
) implements Serializable {
}

