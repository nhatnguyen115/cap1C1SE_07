package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Response.TestHistoryResponse;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import org.springframework.stereotype.Component;

@Component
public class UserTestMapper {
    public TestHistoryResponse toTestHistoryResponse(UserTestAttempt testAttempt) {
        if (testAttempt == null) return null;
        return new TestHistoryResponse(
                testAttempt.getId(),
                testAttempt.getExam().getExamName(),
                testAttempt.getStartTime(),
                testAttempt.getEndTime(),
                testAttempt.getTotalScore()
        );
    }
}
