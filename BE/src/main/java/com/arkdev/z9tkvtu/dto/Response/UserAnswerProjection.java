package com.arkdev.z9tkvtu.dto.Response;

import java.util.Map;

public interface UserAnswerProjection {
    String getContent();
    String getUrl();
    String getMediaType();
    Map<String, Object> getOptions();
    String getCorrectAnswer();
    String getExplanation();
    String getDifficulty();
    String getSelectedAnswer();
}
