package com.arkdev.z9tkvtu.mapper;

import com.arkdev.z9tkvtu.dto.Request.QuestionRequest;
import com.arkdev.z9tkvtu.dto.Response.QuestionResponse;
import com.arkdev.z9tkvtu.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public Question toQuestion(QuestionRequest request) {
        if (request == null) return null;
        Question question = new Question();
        question.setContent(request.getContent());
        question.setOptions(request.getOptions());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setExplanation(request.getExplanation());
        question.setDifficulty(request.getDifficulty());
        question.setOrderNumber(request.getOrderNumber());
        return question;
    }

    public void updateQuestion(Question question, QuestionRequest request) {
        if (question == null || request == null) return;
        question.setContent(request.getContent());
        question.setOptions(request.getOptions());
        question.setCorrectAnswer(request.getCorrectAnswer());
        question.setExplanation(request.getExplanation());
        question.setDifficulty(request.getDifficulty());
        question.setOrderNumber(request.getOrderNumber());
    }

    public QuestionResponse toQuestionResponse(Question question) {
        if (question == null) return null;
        return new QuestionResponse(
                question.getId(),
                question.getContent(),
                question.getOptions(),
                question.getCorrectAnswer(),
                question.getDifficulty(),
                question.getOrderNumber()
        );
    }
}
