package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.response.ImportError;
import com.arkdev.z9tkvtu.exception.ImportException;
import com.arkdev.z9tkvtu.model.*;
import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import com.arkdev.z9tkvtu.util.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class UploadExamService {
    PartRepository partRepository;
    ExamRepository examRepository;
    SectionRepository sectionRepository;
    DataValidator dataValidator;

    public final String NOT_FOUND_SHEET = "Không tìm thấy sheet:";

    @Transactional
    public void addExamFromExcel(MultipartFile file, Integer sectionId, String testType) throws IOException {
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            setExamData(workbook, sectionId, testType);
        }
    }

    private void setExamData(Workbook workbook, Integer sectionId, String testType) {
        Sheet sheet = workbook.getSheet("EXAM");
        if (sheet == null)
            throw new ImportException(
                    "NOT_FOUND_SHEET",
                    new ImportError(
                            "EXAM",
                            null,
                            null,
                            NOT_FOUND_SHEET
                    )
            );
        Row row = sheet.getRow(1);
        Exam exam = mapToExam(row);
        if (sectionId != null) {
            Section section = sectionRepository.findById(sectionId)
                    .orElseThrow(() -> new IllegalArgumentException("Section not found"));
            exam.getSections().add(section);
            section.getExams().add(exam);
        }
        exam.setTestType(Optional.ofNullable(testType)
                .map(TestType::valueOf).orElse(TestType.TEST));
        ImportError error = dataValidator.validateExam(exam, row.getRowNum()  + 1, "EXAM");
        if (error != null)
            throw new ImportException(
                    "ROW_ERROR",
                    error
            );
        setPartsData(workbook, exam);
        examRepository.save(exam);
    }

    private void setPartsData(Workbook workbook, Exam exam) {
        Sheet sheet = workbook.getSheet("PART");
        if (sheet == null)
            throw new ImportException(
                    "NOT_FOUND_SHEET",
                    new ImportError(
                            "PART",
                            null,
                            null,
                            NOT_FOUND_SHEET
                    )
            );
        Iterator<Row> rows = sheet.iterator();
        if (rows.hasNext()) rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            Part part = mapToPart(row);
            ImportError error = dataValidator.validatePart(part, row.getRowNum()  + 1, "PART");
            if (error != null)
                throw new ImportException(
                        "ROW_ERROR",
                        error
                );
            part = partRepository.save(part);
            exam.getParts().add(part);
            setQuestionsData(workbook, part);
        }
    }

    private void setQuestionsData(Workbook workbook, Part part) {
        Sheet sheet = workbook.getSheet(part.getPartName());
        if (sheet == null)
            throw new ImportException(
                    "NOT_FOUND_SHEET",
                    new ImportError(
                            part.getPartName(),
                            null,
                            null,
                            NOT_FOUND_SHEET
                    )
            );
        Iterator<Row> rows = sheet.iterator();
        if (rows.hasNext()) rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            Question question = mapToQuestion(row,part.getPartName());
            ImportError error = dataValidator.validateQuestion(question, row.getRowNum()  + 1, part.getPartName());
            if (error != null)
                throw new ImportException(
                        "ROW_ERROR",
                        error
                );
            part.getQuestions().add(question);
            question.setPart(part);
        }
    }

    private Question mapToQuestion(Row row, String sheetName) {
        Question question = new Question();
        question.setOrderNumber(Optional.ofNullable(getCellValue(row.getCell(0)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        sheetName,
                                        row.getRowNum()  + 1,
                                        "STT",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        question.setContent(Optional.ofNullable(getCellValue(row.getCell(1)))
                .map(String::valueOf).orElse(null));
        question.setOptions(Optional.ofNullable(getCellValue(row.getCell(2)))
                .map(value -> {
                    try {
                        return new ObjectMapper().readValue(value,
                                new TypeReference<Map<String, Object>>() {});
                    } catch (JsonProcessingException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        sheetName,
                                        row.getRowNum()  + 1,
                                        "Lựa Chọn",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .orElse(null));
        question.setCorrectAnswer(Optional.ofNullable(getCellValue(row.getCell(3)))
                .map(String::valueOf).orElse(null));
        question.setExplanation(Optional.ofNullable(getCellValue(row.getCell(4)))
                .map(String::valueOf).orElse(null));
        question.setMedia(Optional.ofNullable(getCellValue(row.getCell(5)))
                .map(type -> mapToMedia(type, row, 6, sheetName)).orElse(null));
        return question;
    }

    private Part mapToPart(Row row) {
        Part part = new Part();
        part.setOrderNumber(Optional.ofNullable(getCellValue(row.getCell(0)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "PART",
                                        row.getRowNum()  + 1,
                                        "STT",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        part.setPartName(Optional.ofNullable(getCellValue(row.getCell(1)))
                .map(String::valueOf).orElse(null));
        part.setDescription(Optional.ofNullable(getCellValue(row.getCell(2)))
                .map(String::valueOf).orElse(null));
        part.setQuestionType(Optional.ofNullable(getCellValue(row.getCell(3)))
                .map(s -> {
                    try {
                        return QuestionType.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "PART",
                                        row.getRowNum()  + 1,
                                        "Loại Câu Hỏi",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .orElse(null));
        part.setInstructions(Optional.ofNullable(getCellValue(row.getCell(4)))
                .map(String::valueOf).orElse(null));
        part.setQuestionCount(Optional.ofNullable(getCellValue(row.getCell(5)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Tổng điểm",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        part.setGradingType(Optional.ofNullable(getCellValue(row.getCell(6)))
                .map(s -> {
                    try {
                        return GradingType.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "PART",
                                        row.getRowNum()  + 1,
                                        "Chấm điểm",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .orElse(null));
        part.setMedia(Optional.ofNullable(getCellValue(row.getCell(7)))
                .map(type -> mapToMedia(type, row, 8, "PART")).orElse(null));
        return part;
    }

    private Media mapToMedia(String type, Row row, int i, String sheetName) {
        Media media = new Media();
        MediaType mediaType = Optional.ofNullable(type)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    try {
                        return MediaType.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        sheetName,
                                        row.getRowNum()  + 1,
                                        "Loại Media",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .orElseThrow(() ->  new ImportException(
                        "ROW_ERROR",
                        new ImportError(
                                sheetName,
                                row.getRowNum()  + 1,
                                "Loại Media",
                                "Kiểu dữ liệu không phù hợp"
                        )
                ));
        media.setMediaType(mediaType);
        media.setUrl(Optional.ofNullable(getCellValue(row.getCell(i)))
                .map(String::valueOf).orElse(null));
        return media;
    }

    private Exam mapToExam(Row row) {
        Exam exam = new Exam();
        exam.setExamName(Optional.ofNullable(getCellValue(row.getCell(0)))
                .map(s -> {
                    try {
                        return s;
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Tên Bài Kiểm Tra ",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                }).orElse(null));
        exam.setTotalScore(Optional.ofNullable(getCellValue(row.getCell(1)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Tổng điểm",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        exam.setDuration(Optional.ofNullable(getCellValue(row.getCell(2)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Thởi gian làm",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        exam.setQuestionCount(Optional.ofNullable(getCellValue(row.getCell(3)))
                .map(n -> {
                    try {
                        return Double.valueOf(n);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Số câu hỏi",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .map(Double::intValue).orElse(null));
        exam.setLevel(Optional.ofNullable(getCellValue(row.getCell(4)))
                .map(s -> {
                    try {
                        return DifficultyLevel.valueOf(s);
                    } catch (IllegalArgumentException e) {
                        throw new ImportException(
                                "ROW_ERROR",
                                new ImportError(
                                        "EXAM",
                                        row.getRowNum()  + 1,
                                        "Cấp độ",
                                        "Kiểu dữ liệu không phù hợp"

                                )
                        );
                    }
                })
                .orElse(DifficultyLevel.BEGINNER));
        return exam;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return null;
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> null;
        };
    }
}
