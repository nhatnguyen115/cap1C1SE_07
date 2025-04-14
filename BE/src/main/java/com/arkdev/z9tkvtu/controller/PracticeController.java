package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.PracticeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/practice")
@RequiredArgsConstructor
public class PracticeController {
    PracticeService practiceService;

    @GetMapping("")
    public ResponseData<?> getPractice(@RequestParam Integer sectionId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Practice Successfully",
                    practiceService.getSectionDetails(sectionId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Practice Failed");
        }
    }
}
