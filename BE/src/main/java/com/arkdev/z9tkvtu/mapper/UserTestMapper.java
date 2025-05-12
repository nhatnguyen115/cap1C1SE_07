package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Response.UserAnswerResponse;
import com.arkdev.z9tkvtu.dto.Response.UserTestHistoryResponse;
import com.arkdev.z9tkvtu.model.UserAnswer;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import org.springframework.stereotype.Component;

@Component
public class UserTestMapper {
    public UserTestHistoryResponse toTestHistoryResponse(UserTestAttempt testAttempt) {
        if (testAttempt == null) return null;
        return new UserTestHistoryResponse(
                testAttempt.getId(),
                testAttempt.getExam().getExamName(),
                testAttempt.getTotalScore(),
                testAttempt.getStartTime(),
                testAttempt.getEndTime()
        );
    }

    public UserAnswerResponse toUserAnswerResponse(UserAnswer userAnswer) {
        if (userAnswer == null) return null;
        return new UserAnswerResponse(
                userAnswer.getQuestion().getContent(),
                userAnswer.getQuestion().getMedia() != null ?
                        userAnswer.getQuestion().getMedia().getUrl() : null,
                userAnswer.getQuestion().getMedia() != null ?
                        userAnswer.getQuestion().getMedia().getMediaType() : null,
                userAnswer.getQuestion().getOptions(),
                userAnswer.getQuestion().getCorrectAnswer(),
                userAnswer.getQuestion().getExplanation(),
                userAnswer.getQuestion().getDifficulty(),
                userAnswer.getSelectedAnswer()
        );
    }
}
