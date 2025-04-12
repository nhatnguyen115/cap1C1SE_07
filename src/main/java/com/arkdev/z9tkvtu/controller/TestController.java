package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.ExamRequest;
import com.arkdev.z9tkvtu.dto.Request.TestRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.dto.Response.TestResponse;
import com.arkdev.z9tkvtu.service.ExamService;
import com.arkdev.z9tkvtu.service.TestService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/tests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {
    TestService testService;
    ExamService examService;

    @GetMapping("")
    public ResponseData<?> getTests(@RequestParam Integer moduleId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Tests Successfully",
                    testService.getTests(moduleId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Tests Failed");
        }
    }

    @GetMapping("/{testId}")
    public ResponseData<?> getTest(@PathVariable Integer testId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Test Successfully",
                    testService.getTest(testId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Test Failed");
        }
    }

    @PostMapping("/{testId}/exams")
    public ResponseData<?> addExam(@PathVariable Integer testId,
                                   @Valid @RequestBody ExamRequest request) {
        try {
            examService.addExam(testId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Exam Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Exam could not be added");
        }
    }

    @PutMapping("/{testId}")
    public ResponseData<?> updateTest(@PathVariable Integer testId,
                                      @Validated @RequestBody TestRequest request) {
        try {
            testService.updateTest(testId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test could not be updated");
        }
    }

    @DeleteMapping("/{testId}")
    public ResponseData<?> deleteTest(@PathVariable Integer testId) {
        try {
            testService.deleteTest(testId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test could not be deleted");
        }
    }
}
