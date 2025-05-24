package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.SectionPartRequest;
import com.arkdev.z9tkvtu.dto.Request.UserAnswerRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.PracticeAttemptService;
import com.arkdev.z9tkvtu.service.PracticeService;
import com.arkdev.z9tkvtu.util.Pagination;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeController {
    PracticeService practiceService;
    PracticeAttemptService practiceAttemptService;

    @GetMapping("")
    public ResponseData<?> getPractice(@RequestParam Integer sectionId,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Practice Successfully",
                    Pagination.paginate(practiceService.getSectionDetails(sectionId), PageRequest.of(page, size)));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Practice Failed");
        }
    }

    @PostMapping("/start-practice")
    public ResponseData<?> startPractice(@RequestParam Integer examId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Start Practice Successfully",
                    practiceAttemptService.startMiniTest(examId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Start Practice failed");
        }
    }

    @PostMapping("/submit-practice")
    public ResponseData<?> submitPractice(@RequestParam Integer attemptId,
                                          @RequestBody List<UserAnswerRequest> answers) {
        try {
            practiceAttemptService.submitMiniTest(attemptId, answers);
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Submit Practice Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Submit Practice failed");
        }
    }
}
