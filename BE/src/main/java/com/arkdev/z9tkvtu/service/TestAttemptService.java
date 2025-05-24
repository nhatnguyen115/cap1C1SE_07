package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.*;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.mapper.UserTestMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.*;
import com.arkdev.z9tkvtu.util.MediaType;
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

import static com.arkdev.z9tkvtu.util.Convert.*;

@Service
public class TestAttemptService extends AttemptService{
    public TestAttemptService(UserTestAttemptRepository userTestAttemptRepository,
                                  UserAnswerRepository userAnswerRepository,
                                  PartRepository partRepository, ExamRepository examRepository,
                                  QuestionRepository questionRepository,
                                  UserTestMapper userTestMapper, PartMapper partMapper,
                                  ExamMapper examMapper) {
        super(userTestAttemptRepository, userAnswerRepository, partRepository,
                examRepository, questionRepository, userTestMapper, partMapper, examMapper);
    }

    public List<UserTestHistoryResponse> getTestHistories() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        return userTestAttemptRepository.findByUser(user)
                .stream()
                .map(userTestMapper::toTestHistoryResponse)
                .toList();
    }

    public TestAttemptResponse getTestAttempt(Integer attemptId) {
        return userTestAttemptRepository.findByIdAndCompleteTrue(attemptId)
                .map(userTestMapper::toAttemptResponse)
                .orElseThrow(() -> new RuntimeException("attempt not found"));
    }

    public List<UserRankResponse> getUserRanks(Integer examId) {
        return userTestAttemptRepository.findByUserOfRank(examId).stream()
                .map(r -> new UserRankResponse(
                        getString(r[0]),
                        getInt(r[1]),
                        getInt(r[2])
                )).toList();
    }

    @Transactional
    public Integer startTest(Integer examId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();

        if (!examRepository.existsById(examId))
            throw new RuntimeException("exam not found");
        Exam exam = examRepository.getReferenceById(examId);
        return start(null, exam, user);
    }

    @Transactional
    public void submitTest(Integer attemptId,
                           List<UserAnswerRequest> answers) {
        UserTestAttempt attempt = submit(attemptId, answers, false);
        List<Integer[]> list = userTestAttemptRepository.answerParameterCalculation(attempt.getId());
        Integer listeningScore = userTestAttemptRepository.findListeningScore(getInt(list.getFirst()[2]));
        Integer readingScore = userTestAttemptRepository.findReadingScore(getInt(list.getFirst()[3]));
        attempt.setCorrectCount(getInt(list.getFirst()[2]) + getInt(list.getFirst()[3]));
        attempt.setIncorrectCount(getInt(list.getFirst()[1]) - (getInt(list.getFirst()[2]) + getInt(list.getFirst()[3])));
        attempt.setSkipCount(getInt(list.getFirst()[0]) - getInt(list.getFirst()[1]));
        attempt.setListeningScore(listeningScore);
        attempt.setReadingScore(readingScore);
        attempt.setTotalScore(listeningScore + readingScore);
    }

    @Transactional
    public void deleteTest(Integer attemptId) {
        UserTestAttempt attempt = userTestAttemptRepository.findById(attemptId)
                        .orElseThrow(() -> new RuntimeException("Attempt not found"));
        userTestAttemptRepository.delete(attempt);
    }
}
