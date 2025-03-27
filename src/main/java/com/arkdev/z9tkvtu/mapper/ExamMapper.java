package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.ExamResponse;
import com.arkdev.z9tkvtu.model.Exam;
import org.springframework.stereotype.Component;

@Component
public class ExamMapper {
    public Exam toExam(ExamRequest request) {
        if (request == null) return null;
        Exam exam = new Exam();
        exam.setExamName(request.getExamName());
        exam.setDuration(request.getDuration());
        exam.setTotalScore(request.getTotalScore());
        return exam;
    }

    public void updateExam(Exam exam, ExamRequest request) {
        if (request == null) return;
        exam.setExamName(request.getExamName());
        exam.setDuration(request.getDuration());
        exam.setTotalScore(request.getTotalScore());
    }

    public ExamResponse toExamResponse(Exam exam) {
        if (exam == null) return null;
        return new ExamResponse(
                exam.getExamName(),
                exam.getDuration(),
                exam.getTotalScore());
    }
}
