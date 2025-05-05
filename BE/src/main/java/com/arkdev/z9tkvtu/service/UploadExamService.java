package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.mapper.MediaMapper;
import com.arkdev.z9tkvtu.mapper.QuestionMapper;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.QuestionRepository;
import com.arkdev.z9tkvtu.repository.TestRepository;
import com.arkdev.z9tkvtu.util.DifficultyLevel;
import com.arkdev.z9tkvtu.util.MediaType;
import com.arkdev.z9tkvtu.util.QuestionType;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UploadExamService {
    QuestionRepository questionRepository;
    PartRepository partRepository;
    ExamRepository examRepository;
    TestRepository testRepository;
    MediaMapper mediaMapper;
    QuestionMapper questionMapper;


    @Transactional
    public void addExamFromExcel(Integer testId, MultipartFile file) throws IOException {
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Test  test = testRepository.findById(testId)
                    .orElseThrow(() -> new RuntimeException("test not found"));
            Row zero = sheet.getRow(0);
            Exam exam = new Exam();
            Part part = new Part();
            Question question = new Question();
            Media media = new Media();
            if (zero.getCell(0).getStringCellValue().equalsIgnoreCase("EXAM")) {
                exam.setExamName(zero.getCell(1).getStringCellValue());
                exam.setDuration(Integer.valueOf(zero.getCell(2).getStringCellValue()));
                exam.setTotalScore(Integer.valueOf(zero.getCell(3).getStringCellValue()));
                test.getExams().add(exam);
            }
            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase("PART")) {
                    part.setPartName(row.getCell(1).getStringCellValue());
                    part.setDescription(row.getCell(2).getStringCellValue());
                    part.setQuestionType(QuestionType.valueOf(row.getCell(3).getStringCellValue()));
                    part.setInstructions(row.getCell(4).getStringCellValue());
                    part.setQuestionCount(Integer.valueOf(row.getCell(5).getStringCellValue()));
                    if (row.getCell(6).getStringCellValue().equalsIgnoreCase("MEDIA")) {
                        media.setMediaType(MediaType.valueOf(row.getCell(7).getStringCellValue()));
                        media.setUrl(row.getCell(8).getStringCellValue());
                        part.setMedia(media);
                    }
                    part = partRepository.save(part);
                    exam.getParts().add(part);
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("QUESTION")) {
                    question.setContent(row.getCell(1).getStringCellValue());
                    question.setOptions(new ObjectMapper().readValue(row.getCell(1).getStringCellValue(),
                            new TypeReference<>() {}));
                    question.setCorrectAnswer(row.getCell(3).getStringCellValue());
                    question.setExplanation(row.getCell(4).getStringCellValue());
                    question.setDifficulty(DifficultyLevel.valueOf(row.getCell(5).getStringCellValue()));
                    if (row.getCell(6).getStringCellValue().equalsIgnoreCase("MEDIA")) {
                        media.setMediaType(MediaType.valueOf(row.getCell(7).getStringCellValue()));
                        media.setUrl(row.getCell(8).getStringCellValue());
                        question.setMedia(media);
                    }
                    part.getQuestions().add(question);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
