package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.model.Exam;
import com.arkdev.z9tkvtu.service.ExamService;
import com.arkdev.z9tkvtu.util.Pagination;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/exam")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExamController {
    ExamService examService;

    @GetMapping("")
    public ResponseData<?> getExams(@RequestParam Integer testId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Exams Successfully",
                    Pagination.paginate(examService.getExams(testId), PageRequest.of(page, size)));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Exams Failed");
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

    @PostMapping("/add")
    public ResponseData<?> addExam(@RequestParam Integer testId,@Valid @RequestBody ExamRequest request) {
        try {
            examService.addExam(testId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be added");
        }
    }

    @PutMapping("/update/{examId}")
    public ResponseData<?> updateExam(@PathVariable Integer examId,
                                      @Valid @RequestBody ExamRequest request) {
        try {
            examService.updateExam(examId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be updated");
        }
    }

    @DeleteMapping("/delete/{examId}")
    public ResponseData<?> deleteExam(@PathVariable Integer examId) {
        try {
            examService.deleteExam(examId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be deleted");
        }
    }
}
