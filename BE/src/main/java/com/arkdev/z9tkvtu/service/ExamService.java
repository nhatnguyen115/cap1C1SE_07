package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.request.ExamRequest;
import com.arkdev.z9tkvtu.dto.response.*;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.mapper.QuestionMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.*;
import com.arkdev.z9tkvtu.util.ResourceType;
import com.arkdev.z9tkvtu.util.TestType;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamService {
    ExamRepository examRepository;
    SectionRepository sectionRepository;
    PartRepository partRepository;
    QuestionRepository questionRepository;
    UserTestAttemptRepository attemptRepository;
    ResourceAccessRepository resourceAccessRepository;
    ExamMapper examMapper;
    PartMapper partMapper;
    QuestionMapper questionMapper;

    @Transactional
    public void changePremium(Integer examId, Boolean premium) {
        ResourceAccess access = resourceAccessRepository.findByResourceIdAndTableName(examId, "EXAM")
                .orElseThrow(() -> new RuntimeException("Resource Not Found"));
        access.setResourceType(premium ? ResourceType.MEMBER : ResourceType.FREE);
    }

    public List<ExamListResponse> getExams(String testType, Integer sectionId) {
        List<Exam> exams;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        if (sectionId == null || sectionId.describeConstable().isEmpty()) {
            exams = examRepository.findAllByTestTypeOrderByCreatedAtDesc(TestType.valueOf(testType));
        } else {
            exams = examRepository.findAllBySectionsIdAndTestTypeOrderByCreatedAtDesc(sectionId, TestType.valueOf(testType));
        }
        return exams.stream()
                .map(exam -> {
                    Integer students = examRepository.countByUserTestAttempt(exam.getId());
                    Integer attemptCount = attemptRepository.countByUserIdAndExamIdAndCompleteTrue(user.getId(), exam.getId());
                    ResourceAccess access = resourceAccessRepository.findByResourceIdAndTableName(exam.getId(), "EXAM")
                            .orElse(null);
                    if (access == null) {
                        access = new ResourceAccess(exam.getId(), "EXAM", ResourceType.FREE);
                        access = resourceAccessRepository.save(access);
                    }
                    return new ExamListResponse(
                            exam.getId(),
                            exam.getExamName(),
                            exam.getTotalScore(),
                            exam.getDuration(),
                            exam.getQuestionCount(),
                            students,
                            exam.getLevel(),
                            attemptCount,
                            Optional.ofNullable(access.getResourceType())
                                    .map(a -> a == ResourceType.MEMBER)
                                    .orElse(false)
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
    public void addExam(ExamRequest request, Integer sectionId) {
        examRepository.findByExamName(request.getExamName())
            .ifPresent(exam -> {
                throw new RuntimeException("Exam name already exists");
            });
        Exam exam = examMapper.toExam(request);
        if (sectionId != null) {
            Section section = sectionRepository.findById(sectionId)
                            .orElseThrow(() -> new IllegalArgumentException("Section not found"));
            exam.getSections().add(section);
            section.getExams().add(exam);
        }
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
