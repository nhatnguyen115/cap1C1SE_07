package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.UserTestAttemptService;
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
    UserTestAttemptService userTestAttemptService;

    @GetMapping("")
    public ResponseData<?> getTestHistories() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Test Histories Successfully",
                    userTestAttemptService.getTestHistories());
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Test Histories Failed");
        }
    }

    @GetMapping("/get-result")
    public ResponseData<?> getTestResult(@RequestParam Integer attemptId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Test Result Successfully",
                    userTestAttemptService.getAttemptDetails(attemptId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Test Result Failed");
        }
    }

    @GetMapping("/user-rank")
    public ResponseData<?> getUserRank(@RequestParam Integer examId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get User Rank Successfully",
                    userTestAttemptService.getUserRanks(examId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get User Rank Failed");
        }
    }

    @PostMapping("/start-test")
    public ResponseData<?> startTest(@RequestParam Integer examId) {
        try {

            return new ResponseData<>(HttpStatus.OK.value(), "Start Test Successfully",
                    userTestAttemptService.startTest(examId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Start Test Failed");
        }
    }

    @PostMapping("/submit-test")
    public ResponseData<?> submitTest(@RequestParam Integer attemptId,
                                      @RequestBody List<UserAnswerRequest> answers) {
        try {
            userTestAttemptService.submitTest(attemptId, answers);
            return new ResponseData<>(HttpStatus.OK.value(), "Submit Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Submit Test Failed");
        }
    }

    @DeleteMapping("/delete-test")
    public ResponseData<?> deleteTest(@RequestParam Integer attemptId) {
        try {
            userTestAttemptService.deleteTest(attemptId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Delete Test Failed");
        }
    }
}
