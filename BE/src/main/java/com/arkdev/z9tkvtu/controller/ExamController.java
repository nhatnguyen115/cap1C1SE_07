package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.request.ExamRequest;
import com.arkdev.z9tkvtu.dto.request.PartRequest;
import com.arkdev.z9tkvtu.dto.response.ImportError;
import com.arkdev.z9tkvtu.dto.response.ResponseData;
import com.arkdev.z9tkvtu.dto.response.ResponseError;
import com.arkdev.z9tkvtu.service.ExamService;
import com.arkdev.z9tkvtu.service.PartService;
import com.arkdev.z9tkvtu.service.UploadExamService;
import com.arkdev.z9tkvtu.util.Pagination;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Validated
@RequestMapping("/exams")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamController {
    ExamService examService;
    PartService partService;
    UploadExamService uploadExamService;

    @GetMapping("")
    public ResponseData<?> getExams(
            @RequestParam(defaultValue = "TEST") String testType,
            @RequestParam(defaultValue = "") Integer sectionId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Exams Successfully",
                    Pagination.paginate(examService.getExams(testType, sectionId), PageRequest.of(page, size)));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Exams Failed");
        }
    }

    @PostMapping("/{examId}/change-premium")
    public ResponseData<?> changePremium(@PathVariable Integer examId, @RequestParam Boolean premium) {
        try {
            examService.changePremium(examId, premium);
            return new ResponseData<>(HttpStatus.OK.value(), "Change Premium Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Change Premium Failed");
        }
    }

    @GetMapping("/{examId}")
    public ResponseData<?> getExam(@PathVariable Integer examId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Exam Successfully",
                    examService.getExam(examId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Exam Failed");
        }
    }

    @PostMapping("")
    public ResponseData<?> addExam(@Valid @RequestBody ExamRequest request,
                                   @RequestParam(required = false) Integer sectionId) {
        try {
            examService.addExam(request, sectionId);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be added");
        }
    }

    @PostMapping("/upload/excel")
    public ResponseData<?> uploadExam(@RequestParam MultipartFile file,
                                      @RequestParam(required = false) Integer sectionId,
                                      @RequestParam(defaultValue = "TEST") String testType) throws IOException {
        uploadExamService.addExamFromExcel(file, sectionId, testType);
        return new ResponseData<>(HttpStatus.OK.value(), "Upload Exam Successfully");
    }

    @PostMapping("/{examId}/parts")
    public ResponseData<?> addPart(@PathVariable Integer examId,
                                   @Valid @RequestBody PartRequest request) {
        try {
            partService.addPartToExam(examId, request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Add Part To Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Part could not be added");
        }
    }

    @PutMapping("/{examId}")
    public ResponseData<?> updateExam(@PathVariable Integer examId,
                                      @Valid @RequestBody ExamRequest request) {
        try {
            examService.updateExam(examId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be updated");
        }
    }

    @DeleteMapping("/{examId}")
    public ResponseData<?> deleteExam(@PathVariable Integer examId) {
        try {
            examService.deleteExam(examId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be deleted");
        }
    }
}
