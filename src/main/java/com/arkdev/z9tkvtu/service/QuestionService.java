package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.MediaRequest;
import com.arkdev.z9tkvtu.dto.Request.QuestionRequest;
import com.arkdev.z9tkvtu.dto.Response.QuestionResponse;
import com.arkdev.z9tkvtu.mapper.MediaMapper;
import com.arkdev.z9tkvtu.mapper.QuestionMapper;
import com.arkdev.z9tkvtu.model.Media;
import com.arkdev.z9tkvtu.model.Part;
import com.arkdev.z9tkvtu.model.Question;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.QuestionRepository;
import com.arkdev.z9tkvtu.util.DifficultyLevel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QuestionService {
    QuestionRepository questionRepository;
    PartRepository partRepository;
    MediaMapper mediaMapper;
    QuestionMapper questionMapper;

    public List<QuestionResponse> getQuestions(Integer partId) {
        return questionRepository.findByPartIdOrderByOrderNumber(partId)
                .stream()
                .map(questionMapper::toQuestionResponse)
                .toList();
    }

    public QuestionResponse getQuestion(Integer questionId) {
        return questionRepository.findById(questionId)
                .map(questionMapper::toQuestionResponse)
                .orElseThrow(() -> new RuntimeException("question not found"));
    }

    public void addQuestion(Integer partId,QuestionRequest request) {
        if (!partRepository.existsById(partId))
            throw new RuntimeException("part not found");
        Part part = partRepository.getReferenceById(partId);
        Question question = questionMapper.toQuestion(request);
        question.setPart(part);
        questionRepository.save(question);
    }

    public void updateQuestion(Integer questionId, QuestionRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("question not found"));
        questionMapper.updateQuestion(question, request);
        questionRepository.save(question);
    }

    public void deleteQuestion(Integer questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("question not found"));
        questionRepository.delete(question);
    }

    public void addMediaToQuestion(Integer questionId, MediaRequest request) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        Media media = mediaMapper.toMedia(request);
        question.setMedia(media);
        questionRepository.save(question);
    }

    public void addQuestionsFromExcel(Integer partId, MultipartFile file) throws IOException {
        Part part = partRepository.findById(partId)
                .orElseThrow(() -> new RuntimeException("part not found"));
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {

                Question question = new Question();
                question.setContent(row.getCell(0).getStringCellValue());
                question.setOptions(new ObjectMapper().readValue(row.getCell(1).getStringCellValue(),
                        new TypeReference<>() {}));
                question.setCorrectAnswer(row.getCell(2).getStringCellValue());
                question.setExplanation(row.getCell(3).getStringCellValue());
                question.setDifficulty(DifficultyLevel.valueOf(row.getCell(4).getStringCellValue()));
                part.getQuestions().add(question);
            }
            partRepository.save(part);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
