package com.arkdev.z9tkvtu.service;


import com.arkdev.z9tkvtu.dto.Request.SectionPartRequest;
import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.*;
import com.arkdev.z9tkvtu.mapper.LessonMapper;
import com.arkdev.z9tkvtu.mapper.PartMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.*;
import com.arkdev.z9tkvtu.util.DifficultyLevel;
import com.arkdev.z9tkvtu.util.MediaType;
import com.arkdev.z9tkvtu.util.TestType;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.arkdev.z9tkvtu.util.Convert.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PracticeService {
    LessonRepository lessonRepository;
    PartRepository partRepository;
    SectionPartPracticeRepository practiceRepository;
    ExamRepository examRepository;
    SectionPartAnswerRepository answerRepository;
    QuestionRepository questionRepository;
    LessonMapper lessonMapper;
    PartMapper partMapper;

    public List<ExamListResponse> getSectionDetails(Integer sectionId) {
        return examRepository.findAllBySectionsIdAndTestTypeOrderByCreatedAtDesc(sectionId, TestType.MINITEST)
                .stream()
                .map(exam -> new ExamListResponse(
                        exam.getId(),
                        exam.getExamName(),
                        exam.getTotalScore(),
                        exam.getDuration(),
                        exam.getQuestionCount(),
                        null,
                        exam.getLevel()
                ))
                .toList();
    }

    public PartAttemptResponse<?, ?> getPracticeResult(Integer partId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        SectionPartPractice practice = practiceRepository.findByUserIdAndPartId(user.getId(), partId)
                .orElse(null);
        if (practice == null) {
            Part part = partRepository.findById(partId)
                    .orElseThrow(() -> new RuntimeException("Part Not Found"));
            PartDetailsResponse partDetailsResponse = partMapper.toPartDetailsResponse(part);
            return new PartAttemptResponse<>(
                    partDetailsResponse,
                    null
            );
        }
        PartDetailsResponse partDetailsResponse = partMapper.toPartDetailsResponse(practice);
        List<UserAnswerResponse> answerResponses = answerRepository.findByPracticeIdWithPartId(practice.getId(), partId)
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
        return new PartAttemptResponse<>(
                partDetailsResponse,
                answerResponses
        );
    }

    @Transactional
    public void submitPractice(SectionPartRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        SectionPartPractice practice = practiceRepository.findByUserIdAndPartId(user.getId(),
                        request.getPartId())
                .orElse(new SectionPartPractice());
        Part part = partRepository.getReferenceById(request.getPartId());
        practice.setPart(part);
        practice.setUser(user);
        practice.setTotalTime(request.getTotalTime());
        List<Integer> questionIds = request.getAnswers().stream()
                .map(UserAnswerRequest::getQuestionId)
                .toList();

        Map<Integer, Question> questions = questionRepository.findAllById(questionIds)
                .stream()
                .collect(Collectors.toMap(Question::getId, Function.identity()));
        AtomicInteger count = new AtomicInteger();
        List<SectionPartAnswer> partAnswers = request.getAnswers().stream()
                .map(answerRequest -> {
                    Question question = Optional.ofNullable(questions.get(answerRequest.getQuestionId()))
                            .orElseThrow(() -> new RuntimeException("Question not found"));
                    if (question.getCorrectAnswer()
                            .equals(answerRequest.getSelectedAnswer()))
                        count.set(count.get() + 1);
                    return answerRepository.findByQuestionIdAndPracticeId(question.getId(), practice.getId())
                            .map(answer -> {
                                answer.setSelectedAnswer(answerRequest.getSelectedAnswer());
                                return answer;
                            })
                            .orElse(new SectionPartAnswer(
                                    practice,
                                    question,
                                    answerRequest.getSelectedAnswer()
                            ));
                }).toList();
        practice.setCorrectCount(count.get());
        practiceRepository.save(practice);
        answerRepository.saveAll(partAnswers);
    }
}
