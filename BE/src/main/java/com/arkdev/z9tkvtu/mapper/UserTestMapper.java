package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Response.MiniTestAttemptResponse;
import com.arkdev.z9tkvtu.dto.Response.TestAttemptResponse;
import com.arkdev.z9tkvtu.dto.Response.UserTestHistoryResponse;
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

    public TestAttemptResponse toAttemptResponse(UserTestAttempt testAttempt) {
        if (testAttempt == null) return null;
        return new TestAttemptResponse(
                testAttempt.getId(),
                testAttempt.getTotalScore(),
                testAttempt.getListeningScore(),
                testAttempt.getReadingScore(),
                testAttempt.getCorrectCount(),
                testAttempt.getIncorrectCount(),
                testAttempt.getSkipCount(),
                testAttempt.getStartTime(),
                testAttempt.getEndTime()
        );
    }

    public MiniTestAttemptResponse toMiniAttemptResponse(UserTestAttempt testAttempt) {
        if (testAttempt == null) return null;
        return new MiniTestAttemptResponse(
                testAttempt.getId(),
                testAttempt.getTotalScore(),
                testAttempt.getCorrectCount(),
                testAttempt.getIncorrectCount(),
                testAttempt.getSkipCount(),
                testAttempt.getStartTime(),
                testAttempt.getEndTime()
        );
    }
}
