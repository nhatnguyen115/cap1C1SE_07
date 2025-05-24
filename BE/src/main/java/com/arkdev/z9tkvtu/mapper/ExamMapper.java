package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.ExamDetailsResponse;
import com.arkdev.z9tkvtu.dto.Response.ExamResponse;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper {
    public Exam toExam(ExamRequest request) {
        if (request == null) return null;
        Exam exam = new Exam();
        exam.setExamName(request.getExamName());
        exam.setDuration(request.getDuration());
        exam.setTotalScore(request.getTotalScore());
        exam.setQuestionCount(request.getQuestionCount());
        exam.setLevel(request.getLevel());
        exam.setTestType(request.getTestType());
        return exam;
    }

    public void updateExam(Exam exam, ExamRequest request) {
        if (request == null) return;
        exam.setExamName(request.getExamName());
        exam.setDuration(request.getDuration());
        exam.setTotalScore(request.getTotalScore());
        exam.setQuestionCount(request.getQuestionCount());
        exam.setLevel(request.getLevel());
        exam.setTestType(request.getTestType());
    }

    public ExamResponse toExamResponse(Exam exam) {
        if (exam == null) return null;
        return new ExamResponse(
                exam.getId(),
                exam.getExamName(),
                exam.getTotalScore(),
                exam.getDuration(),
                exam.getQuestionCount(),
                exam.getLevel());
    }

    public ExamDetailsResponse toExamDetailsResponse(UserTestAttempt attempt) {
        if (attempt == null) return null;
        if (attempt.getExam() == null) return null;
        return new ExamDetailsResponse(
                attempt.getExam().getId(),
                attempt.getExam().getExamName(),
                attempt.getExam().getDuration(),
                attempt.getTotalScore(),
                attempt.getListeningScore(),
                attempt.getReadingScore(),
                attempt.getCorrectCount(),
                attempt.getIncorrectCount(),
                attempt.getSkipCount(),
                attempt.getStartTime(),
                attempt.getEndTime()
        );
    }
}
