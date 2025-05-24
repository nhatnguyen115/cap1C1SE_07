package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.MiniTestAttemptResponse;
import com.arkdev.z9tkvtu.mapper.ExamMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.mapper.UserTestMapper;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import com.arkdev.z9tkvtu.repository.*;
import com.arkdev.z9tkvtu.util.TestType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.arkdev.z9tkvtu.util.Convert.getInt;

@Service
public class PracticeAttemptService extends AttemptService {
    public PracticeAttemptService(UserTestAttemptRepository userTestAttemptRepository,
                                  UserAnswerRepository userAnswerRepository,
                                  PartRepository partRepository, ExamRepository examRepository,
                                  QuestionRepository questionRepository,
                                  UserTestMapper userTestMapper, PartMapper partMapper,
                                  ExamMapper examMapper) {
        super(userTestAttemptRepository, userAnswerRepository, partRepository,
                examRepository, questionRepository, userTestMapper, partMapper, examMapper);
    }

    public MiniTestAttemptResponse getMiniTestAttempt(Integer attemptId) {
        return userTestAttemptRepository.findByIdAndCompleteTrue(attemptId)
                .map(userTestMapper::toMiniAttemptResponse)
                .orElseThrow(() -> new RuntimeException("attempt not found"));
    }

    @Transactional
    public Integer startMiniTest(Integer examId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        UserTestAttempt testAttempt = userTestAttemptRepository.findByUserIdAndExamIdAndExamTestType(user.getId(), examId, TestType.MINITEST)
                .orElse(null);
        if (!examRepository.existsById(examId))
            throw new RuntimeException("exam not found");
        Exam exam = examRepository.getReferenceById(examId);
        return start(testAttempt, exam, user);
    }

    @Transactional
    public void submitMiniTest(Integer attemptId,
                                      List<UserAnswerRequest> answers) {
        UserTestAttempt attempt = submit(attemptId, answers, true);
        List<Integer[]> list = userTestAttemptRepository.answerPracticeCalculation(attempt.getId());
        attempt.setCorrectCount(getInt(list.getFirst()[2]));
        attempt.setIncorrectCount(getInt(list.getFirst()[1]) - getInt(list.getFirst()[2]));
        attempt.setSkipCount(getInt(list.getFirst()[0]) - getInt(list.getFirst()[1]));
        attempt.setTotalScore(getInt(list.getFirst()[2]) * 5);
    }
}
