package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.response.ResponseData;
import com.arkdev.z9tkvtu.dto.response.ResponseError;
import com.arkdev.z9tkvtu.service.GrammarService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/grammars")
public class GrammarController {
    GrammarService grammarService;

    @GetMapping("")
    public ResponseData<?> getGrammars(@RequestParam("sectionId") Integer sectionId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Grammars Successfully",
                    grammarService.getGrammars(sectionId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Grammars Failed");
        }
    }
}
