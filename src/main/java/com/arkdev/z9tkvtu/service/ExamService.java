package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.ExamResponse;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.Test;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.TestRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamService {
    TestRepository testRepository;
    ExamRepository examRepository;
    ExamMapper examMapper;

    public List<ExamResponse> getExams(Integer testId) {
        return examRepository.findByTestIdOrderByExamName(testId)
                .stream()
                .map(examMapper::toExamResponse)
                .toList();
    }

    public ExamResponse getExam(Integer examId) {
        return examRepository.findById(examId)
                .map(examMapper::toExamResponse)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
    }

    @Transactional
    public void addExam(Integer testId, ExamRequest request) {
        examRepository.findByExamName(request.getExamName())
            .ifPresent(exam -> {
                throw new RuntimeException("Exam name already exists");
            });
        if (!testRepository.existsById(testId))
            throw new RuntimeException("test not found");
        Test test = testRepository.getReferenceById(testId);
        Exam exam = examMapper.toExam(request);
        exam.setTest(test);
        examRepository.save(exam);
    }

    @Transactional
    public void updateExam(Integer examId, ExamRequest request) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        examMapper.updateExam(exam, request);
    }

    @Transactional
    public void deleteExam(Integer examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        examRepository.delete(exam);
    }
}
