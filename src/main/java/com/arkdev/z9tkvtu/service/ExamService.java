package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.ExamResponse;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.Test;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamService {
    TestRepository testRepository;
    ExamRepository examRepository;
    ExamMapper examMapper;

    public List<ExamResponse> getExams() {
        return examRepository.findAll()
                .stream()
                .map(examMapper::toExamResponse)
                .toList();
    }

    public ExamResponse getExam(Integer examId) {
        return examRepository.findById(examId)
                .map(examMapper::toExamResponse)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public void addExam(Integer testId, ExamRequest request) {
        Exam exam = examRepository.findByExamName(request.getExamName())
                .orElse(null);
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        if (exam != null)
            throw new RuntimeException("Exam already exists");
        exam = examMapper.toExam(request);
        test.getExams().add(exam);
        testRepository.save(test);
    }

    public void updateExam(Integer examId, ExamRequest request) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        examMapper.updateExam(exam, request);
        examRepository.save(exam);
    }

    public void deleteExam(Integer examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        examRepository.delete(exam);
    }
}
