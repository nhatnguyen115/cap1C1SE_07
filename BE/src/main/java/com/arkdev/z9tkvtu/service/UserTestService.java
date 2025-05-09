package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.UserResultResponse;
import com.arkdev.z9tkvtu.dto.Response.UserTestHistoryResponse;
import com.arkdev.z9tkvtu.mapper.UserTestMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserTestService {
    UserTestAttemptRepository userTestAttemptRepository;
    UserAnswerRepository userAnswerRepository;
    ExamRepository examRepository;
    QuestionRepository questionRepository;
    UserTestMapper userTestMapper;

    public List<UserTestHistoryResponse> getTestHistories() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        return userTestAttemptRepository.findByUser(user)
                .stream()
                .map(userTestMapper::toTestHistoryResponse)
                .toList();
    }

    public UserResultResponse getTestResult(Integer attemptId) {
        List<Object[]> results = userTestAttemptRepository.findByAttemptWithAnswersByAttemptId(attemptId);
        return mapToUserResultResponse(results);
    }

    @Transactional
    public Integer startTest(Integer examId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();

        if (!examRepository.existsById(examId))
            throw new RuntimeException("exam not found");
        Exam exam = examRepository.getReferenceById(examId);

        UserTestAttempt testAttempt = new UserTestAttempt();
        testAttempt.setExam(exam);
        testAttempt.setUser(user);
        testAttempt.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        testAttempt = userTestAttemptRepository.save(testAttempt);
        return testAttempt.getId();
    }

    @Transactional
    public void submitTest(Integer attemptId, List<UserAnswerRequest> answers) {
        UserTestAttempt attempt = userTestAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
        attempt.setEndTime(Timestamp.valueOf(LocalDateTime.now()));

        List<Integer> questionIds = answers.stream()
                .map(UserAnswerRequest::getQuestionId)
                .toList();

        Map<Integer, Question> questions = questionRepository.findAllById(questionIds)
                .stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));

        List<UserAnswer> userAnswers = answers.stream()
                .map(answerRequest -> {
                    Question question = Optional.ofNullable(questions.get(answerRequest.getQuestionId()))
                            .orElseThrow(() -> new RuntimeException("Question not found"));
                    return new UserAnswer(
                            attempt,
                            question,
                            answerRequest.getSelectedAnswer()
                    );
                }).toList();

        userAnswerRepository.saveAll(userAnswers);
    }

    @Transactional
    public void deleteTest(Integer attemptId) {
        UserTestAttempt attempt = userTestAttemptRepository.findById(attemptId)
                        .orElseThrow(() -> new RuntimeException("Attempt not found"));
        userTestAttemptRepository.delete(attempt);
    }

    private UserResultResponse mapToUserResultResponse(List<Object[]> data) {
        String examName = null;
        int totalScore = 0;
        String partName;
        Map<String, List<UserResultResponse.AnswerResponse>> map = new LinkedHashMap<>();

        for (Object[] row : data) {
            examName = (String) row[0];
            totalScore = (Integer) row[1];
            partName = (String) row[2];
            UserResultResponse.AnswerResponse response = new UserResultResponse.AnswerResponse(
                    (String) row[3],
                    (Map<String, String>) row[4],
                    (String) row[5],
                    (String) row[6],
                    (String) row[7]
            );

            map.computeIfAbsent(partName, k -> new ArrayList<>()).add(response);
        }

        List<UserResultResponse.PartAnswer> partAnswers = map.entrySet().stream()
                .map(e -> new UserResultResponse.PartAnswer(e.getKey(), e.getValue()))
                .toList();
        return new UserResultResponse(examName, totalScore, partAnswers);
    }
}
