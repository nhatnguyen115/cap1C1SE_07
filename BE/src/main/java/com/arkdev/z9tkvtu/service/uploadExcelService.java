package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.repository.ExamRepository;
import com.arkdev.z9tkvtu.repository.PartRepository;
import com.arkdev.z9tkvtu.repository.QuestionRepository;
import com.arkdev.z9tkvtu.repository.SectionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class uploadExcelService {
    ExamRepository examRepository;
    PartRepository partRepository;
    QuestionRepository questionRepository;
    SectionRepository sectionRepository;

    @Transactional
    public void addExamFromExcel(MultipartFile file, Integer sectionId, String testType) {
        try (Workbook workbook= new XSSFWorkbook(file.getInputStream())) {
            setExamData(workbook, sectionId, testType);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void setExamData(Workbook workbook, Integer sectionId, String testType) {
    }
}
