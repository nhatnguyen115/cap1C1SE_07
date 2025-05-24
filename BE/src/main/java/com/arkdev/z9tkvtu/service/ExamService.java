package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.*;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.mapper.QuestionMapper;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.Part;
import com.arkdev.z9tkvtu.model.Question;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.QuestionRepository;
import com.arkdev.z9tkvtu.util.TestType;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamService {
    ExamRepository examRepository;
    PartRepository partRepository;
    QuestionRepository questionRepository;
    ExamMapper examMapper;
    PartMapper partMapper;
    QuestionMapper questionMapper;

    public List<ExamListResponse> getExams(String testType, Integer sectionId) {
        List<Exam> exams;
        if (sectionId == null || sectionId.describeConstable().isEmpty()) {
            exams = examRepository.findAllByTestTypeOrderByCreatedAtDesc(TestType.valueOf(testType));
        } else {
            exams = examRepository.findAllBySectionsIdAndTestTypeOrderByCreatedAtDesc(sectionId, TestType.valueOf(testType));
        }
        return exams.stream()
                .map(exam -> {
                    Integer students = examRepository.countByUserTestAttempt(exam.getId());
                    return new ExamListResponse(
                            exam.getId(),
                            exam.getExamName(),
                            exam.getTotalScore(),
                            exam.getDuration(),
                            exam.getQuestionCount(),
                            students,
                            exam.getLevel()
                    );
                })
                .toList();
    }

    public ExamContentResponse<?> getExam(Integer examId) {
        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new IllegalArgumentException("Exam not found"));
        ExamResponse examResponse = examMapper.toExamResponse(exam);
        List<Part> parts = partRepository.findByExamsIdOrderByOrderNumber(examId);
        List<PartAttemptResponse<?, ?>> partAttemptResponses = new ArrayList<>();
        for (Part part : parts) {
            PartResponse partResponse = partMapper.toPartResponse(part);
            List<Question> questions = questionRepository.findByPartIdOrderByOrderNumber(part.getId());
            List<QuestionResponse> questionResponses = new ArrayList<>();
            for (Question question : questions) {
                QuestionResponse questionResponse = questionMapper.toQuestionResponse(question);
                questionResponses.add(questionResponse);
            }
            partAttemptResponses.add(new PartAttemptResponse<>(partResponse, questionResponses));
        }
        return new ExamContentResponse<>(
                examResponse,
                partAttemptResponses
        );
    }

    @Transactional
    public void addExam(ExamRequest request) {
        examRepository.findByExamName(request.getExamName())
            .ifPresent(exam -> {
                throw new RuntimeException("Exam name already exists");
            });
        Exam exam = examMapper.toExam(request);
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
