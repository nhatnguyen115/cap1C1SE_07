package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.UserTestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/user-test")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserTestController {
    UserTestService userTestService;

    @GetMapping("")
    public ResponseData<?> getTestHistories() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Test Histories Successfully",
                    userTestService.getTestHistories());
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Test Histories Failed");
        }
    }

    @PostMapping("/start-test")
    public ResponseData<?> startTest(@RequestParam Integer examId) {
        try {
            userTestService.startTest(examId);
            return new ResponseData<>(HttpStatus.OK.value(), "Start Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Start Test Failed");
        }
    }

    @PostMapping("/submit-test")
    public ResponseData<?> submitTest(@RequestParam Integer attemptId,
                                      @RequestBody List<UserAnswerRequest> answers) {
        try {
            userTestService.submitTest(attemptId, answers);
            return new ResponseData<>(HttpStatus.OK.value(), "Submit Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Submit Test Failed");
        }
    }
}
