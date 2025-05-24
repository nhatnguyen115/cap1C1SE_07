package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.*;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.mapper.UserTestMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.*;
import com.arkdev.z9tkvtu.util.MediaType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.arkdev.z9tkvtu.util.Convert.*;
import static com.arkdev.z9tkvtu.util.Convert.getString;
import static com.arkdev.z9tkvtu.util.Convert.parseOptions;

@FieldDefaults(level = AccessLevel.PROTECTED,  makeFinal = true)
@RequiredArgsConstructor
public abstract class AttemptService {
    UserTestAttemptRepository userTestAttemptRepository;
    UserAnswerRepository userAnswerRepository;
    PartRepository partRepository;
    ExamRepository examRepository;
    QuestionRepository questionRepository;
    UserTestMapper userTestMapper;
    PartMapper partMapper;
    ExamMapper examMapper;

    public Integer start(UserTestAttempt testAttempt, Exam exam, UserLoginData user) {
        if  (testAttempt == null) {
            testAttempt = new UserTestAttempt();
            testAttempt.setExam(exam);
            testAttempt.setUser(user);
            testAttempt.setComplete(false);
            testAttempt = userTestAttemptRepository.save(testAttempt);
        }
        testAttempt.setStartTime(Timestamp.valueOf(LocalDateTime.now()));
        return testAttempt.getId();
    }

    public UserTestAttempt submit(Integer attemptId,
                                  List<UserAnswerRequest> answers, Boolean isPractice) {
        UserTestAttempt attempt = userTestAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));
        attempt.setEndTime(Timestamp.valueOf(LocalDateTime.now()));
        attempt.setComplete(true);

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
                    if (isPractice) {
                        UserAnswer userAnswer = userAnswerRepository.findByQuestionIdAndAttemptId(question.getId(), attemptId)
                                .orElse(new UserAnswer());
                        userAnswer.setAttempt(attempt);
                        userAnswer.setQuestion(question);
                        userAnswer.setSelectedAnswer(answerRequest.getSelectedAnswer());
                        return userAnswer;
                    }
                    return new UserAnswer(
                            attempt,
                            question,
                            answerRequest.getSelectedAnswer()
                    );
                }).toList();
        userAnswerRepository.saveAll(userAnswers);
        return attempt;
    }

    public AttemptDetailsResponse<?> getDetails(Integer attemptId) {
        UserTestAttempt attempt = userTestAttemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("attempt not found"));
        List<Part> parts = partRepository.findByExamsIdOrderByOrderNumber(attempt.getExam().getId());
        ExamDetailsResponse detailsResponse = examMapper.toExamDetailsResponse(attempt);
        List<PartAttemptResponse<?, ?>> partAttemptResponses = new ArrayList<>();
        for (Part part : parts) {
            PartResponse partResponse = partMapper.toPartResponse(part);
            List<UserAnswerResponse> answerResponses = userAnswerRepository.findByUserAnswerWithPartId(part.getId(), attemptId)
                    .stream().map(r -> new UserAnswerResponse(
                            getInt(r[0]),
                            getString(r[1]),
                            getString(r[2]),
                            getEnum(MediaType.class, r[3]),
                            parseOptions(getString(r[4])),
                            getString(r[5]),
                            getString(r[6]),
                            getString(r[7])
                    )).toList();
            partAttemptResponses.add(new PartAttemptResponse<>(partResponse, answerResponses));
        }
        return new AttemptDetailsResponse<>(
                detailsResponse,
                partAttemptResponses
        );
    }
}
