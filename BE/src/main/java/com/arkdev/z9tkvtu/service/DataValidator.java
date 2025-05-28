package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.response.ImportError;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.model.Part;
import com.arkdev.z9tkvtu.model.Question;
import org.springframework.stereotype.Service;

@Service
class DataValidator {
    public ImportError validateExam(Exam exam, int rowNumber, String sheetName) {
        if (exam.getExamName() ==  null || exam.getExamName().isEmpty()) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Tên bài kiểm tra",
                    "Tên bài kiểm tra không được để trống"
            );
        }
        if (exam.getTotalScore() ==  null || exam.getTotalScore() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Tổng điểm",
                    "Tổng điểm phải lớn hơn 0"
            );
        }
        if (exam.getDuration() ==  null || exam.getDuration() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Thời gian làm",
                    "Thời gian làm phải lớn hơn 0"
            );
        }
        if (exam.getQuestionCount() == null || exam.getQuestionCount() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Số lượng câu hỏi",
                    "Số lượng câu hỏi phải lớn hơn 0"
            );
        }
        return null;
    }

    public ImportError validatePart(Part part, int rowNumber, String sheetName) {
        if (part.getOrderNumber() == null || part.getOrderNumber() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "STT",
                    "STT phải lớn hơn 0"
            );
        }
        if (part.getPartName() == null || part.getPartName().isEmpty()) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Tên phần",
                    "Tên phần không được bỏ trống"
            );
        }
        if (part.getQuestionType() == null) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Loại câu hỏi",
                    "Loại câu hỏi không thể để trống hoặc sai kiểu"
            );
        }
        if (part.getQuestionCount() == null || part.getQuestionCount() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Số lượng câu hỏi",
                    "Số lượng câu hỏi phải lớn hơn 0"
            );
        }
        if (part.getGradingType() == null) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Chấm điểm",
                    "Kiểu chấm điểm không được để trống hoặc sai kiểu"
            );
        }
        return null;
    }

    public ImportError validateQuestion(Question question, int rowNumber, String sheetName) {
        if (question.getOrderNumber() == null || question.getOrderNumber() <= 0) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "STT",
                    "STT phải là số và lớn hơn 0"
            );
        }
        if (question.getOptions() == null) {
            return new ImportError(
                    sheetName,
                    rowNumber,
                    "Lựa chọn",
                    "Lựa chọn không được để trống và phải là kiểu json, kiểm tra cú pháp"
            );
        }
        return null;
    }
}
