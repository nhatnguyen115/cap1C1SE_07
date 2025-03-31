package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.TestRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.dto.Response.TestResponse;
import com.arkdev.z9tkvtu.service.TestService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/test")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestController {
    TestService testService;

    @GetMapping("")
    public ResponseData<?> getTests() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Tests Successfully",
                    testService.getTests());
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

    @PostMapping("/add")
    public ResponseData<?> addTest(@Validated @RequestBody TestRequest request) {
        try {
            testService.addTest(request);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test could not be added");
        }
    }

    @PutMapping("/update/{testId}")
    public ResponseData<?> updateTest(@PathVariable Integer testId,
                                      @Validated @RequestBody TestRequest request) {
        try {
            testService.updateTest(testId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test could not be updated");
        }
    }

    @DeleteMapping("/delete/{testId}")
    public ResponseData<?> deleteTest(@PathVariable Integer testId) {
        try {
            testService.deleteTest(testId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Test Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Test could not be deleted");
        }
    }
}
