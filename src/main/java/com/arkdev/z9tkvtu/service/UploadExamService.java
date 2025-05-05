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
    PartRepository partRepository;
    TestRepository testRepository;


    @Transactional
    public void addExamFromExcel(Integer testId, MultipartFile file) throws IOException {
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            Test test = testRepository.findById(testId)
                    .orElseThrow(() -> new RuntimeException("test not found"));
            Exam exam = new Exam();
            Part part = new Part();
            Question question = new Question();
            Media media = new Media();
            for (Row row : sheet) {
                if (row.getCell(0).getStringCellValue().equalsIgnoreCase("EXAM")) {
                    exam.setExamName(row.getCell(1).getStringCellValue());
                    exam.setDuration((int) row.getCell(2).getNumericCellValue());
                    exam.setTotalScore((int) row.getCell(3).getNumericCellValue());
                    test.getExams().add(exam);
                    exam.setTest(test);
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("PART")) {
                    part.setPartName(row.getCell(1).getStringCellValue());
                    part.setDescription(row.getCell(2).getStringCellValue());
                    part.setQuestionType(QuestionType.valueOf(row.getCell(3).getStringCellValue()));
                    part.setInstructions(row.getCell(4).getStringCellValue());
                    part.setQuestionCount((int) row.getCell(5).getNumericCellValue());
                    part.setOrderNumber((int) row.getCell(6).getNumericCellValue());
                    if (row.getCell(7) != null) {
                        media.setMediaType(MediaType.valueOf(row.getCell(7).getStringCellValue()));
                        media.setUrl(row.getCell(8).getStringCellValue());
                        part.setMedia(media);
                    }
                    part = partRepository.save(part);
                    exam.getParts().add(part);
                } else if (row.getCell(0).getStringCellValue().equalsIgnoreCase("QUESTION")) {
                    question.setContent(row.getCell(1).getStringCellValue());
                    question.setOptions(new ObjectMapper().readValue(row.getCell(2).getStringCellValue(),
                            new TypeReference<>() {}));
                    question.setCorrectAnswer(row.getCell(3).getStringCellValue());
                    question.setExplanation(row.getCell(4).getStringCellValue());
                    question.setDifficulty(DifficultyLevel.valueOf(row.getCell(5).getStringCellValue()));
                    if (row.getCell(6) != null) {
                        media.setMediaType(MediaType.valueOf(row.getCell(6).getStringCellValue()));
                        media.setUrl(row.getCell(7).getStringCellValue());
                        question.setMedia(media);
                    }
                    part.getQuestions().add(question);
                    question.setPart(part);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
