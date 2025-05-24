package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import com.arkdev.z9tkvtu.util.DifficultyLevel;
import com.arkdev.z9tkvtu.util.GradingType;
import com.arkdev.z9tkvtu.util.MediaType;
import com.arkdev.z9tkvtu.util.QuestionType;
import com.fasterxml.jackson.core.JsonProcessingException;
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

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UploadExamService {
    PartRepository partRepository;
    ExamRepository examRepository;
    SectionRepository sectionRepository;

    @Transactional
    public void addExamFromExcel(MultipartFile file, Integer sectionId) {
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            setExamData(workbook, sectionId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    private void setExamData(Workbook workbook, Integer sectionId) throws JsonProcessingException {
        Row row = workbook.getSheet("EXAM").getRow(1);
        Exam exam = new Exam();
        exam.setExamName(row.getCell(0) == null ? "EXAM" : row.getCell(0).getStringCellValue());
        exam.setTotalScore(row.getCell(1) == null ? 0 : (int) row.getCell(1).getNumericCellValue());
        exam.setDuration(row.getCell(2) == null ? 10 : (int) row.getCell(2).getNumericCellValue());
        exam.setQuestionCount(row.getCell(3) == null ? 0 : (int) row.getCell(3).getNumericCellValue());
        exam.setLevel(row.getCell(4) == null ? DifficultyLevel.BEGINNER : DifficultyLevel.valueOf(row.getCell(4).getStringCellValue()));
        if (sectionId != null) {
            Section section = sectionRepository.findById(sectionId)
                    .orElseThrow(() -> new IllegalArgumentException("Section not found"));
            exam.getSections().add(section);
            section.getExams().add(exam);
        }
        setPartsData(workbook, exam);
        examRepository.save(exam);
    }

    private void setPartsData(Workbook workbook, Exam exam) throws JsonProcessingException {
        Sheet sheet = workbook.getSheet("PART");
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            if (String.valueOf(row.getCell(0).getNumericCellValue()).isEmpty()) continue;
            Part part = new Part();
            part.setOrderNumber((int) row.getCell(0).getNumericCellValue());
            part.setPartName(row.getCell(1) == null ? "PART" : row.getCell(1).getStringCellValue());
            part.setDescription(row.getCell(2).getStringCellValue() == null ? "" : row.getCell(2).getStringCellValue());
            part.setQuestionType(row.getCell(3) == null ? QuestionType.MULTIPLE_CHOICE : QuestionType.valueOf(row.getCell(3).getStringCellValue()));
            part.setInstructions(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
            part.setQuestionCount(row.getCell(5) == null ? 0 : (int) row.getCell(5).getNumericCellValue());
            part.setGradingType(row.getCell(6) == null ? GradingType.OTHER : GradingType.valueOf(row.getCell(6).getStringCellValue()));
            part.setMedia(setMediaData(row, 7));
            part = partRepository.save(part);
            exam.getParts().add(part);
            setQuestionsData(workbook, part);
        }
    }

    private void setQuestionsData(Workbook workbook, Part part) throws JsonProcessingException {
        Sheet sheet = workbook.getSheet(part.getPartName());
        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;
            Question question = new Question();
            question.setOrderNumber((int) row.getCell(0).getNumericCellValue());
            question.setContent(row.getCell(1) == null ? "" : row.getCell(1).getStringCellValue());
            question.setOptions(row.getCell(2) == null ? null : new ObjectMapper().readValue(row.getCell(2)
                    .getStringCellValue(), new TypeReference<>() {}));
            question.setCorrectAnswer(row.getCell(3) == null ? "" : row.getCell(3).getStringCellValue());
            question.setExplanation(row.getCell(4) == null ? "" : row.getCell(4).getStringCellValue());
            question.setMedia(setMediaData(row, 5));
            part.getQuestions().add(question);
            question.setPart(part);
        }
    }
    private Media setMediaData(Row row, int i) {
        if (row.getCell(i) != null &&
                !row.getCell(i).getStringCellValue().isEmpty() &&
                !row.getCell(i).getStringCellValue().isBlank()) {
            Media media = new Media();
            media.setMediaType(row.getCell(i) == null ? null : MediaType.valueOf(row.getCell(i).getStringCellValue()));
            media.setUrl(row.getCell(i) == null ? "" : row.getCell(i).getStringCellValue());
            return media;
        }
        return null;
    }
}
