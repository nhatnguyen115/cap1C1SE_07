package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.SectionRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.SectionService;
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
@RequestMapping("/section")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SectionController {
    SectionService sectionService;

    @GetMapping("")
    public ResponseData<?> getSections(@RequestParam Integer moduleId,
                                       @RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Sections Successfully",
                    Pagination.paginate(sectionService.getSections(moduleId), PageRequest.of(page, size)));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Sections Failed");
        }
    }

    @GetMapping("/{sectionId}")
    public ResponseData<?> getSection(@PathVariable Integer sectionId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Section Successfully",
                    sectionService.getSection(sectionId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Section Failed");
        }
    }

    @PostMapping("/add")
    public ResponseData<?> addSection(@RequestParam Integer moduleId,
                                      @Valid @RequestBody SectionRequest sectionRequest) {
        try {
            sectionService.addSection(moduleId, sectionRequest);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Section Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Section could not be added");
        }
    }

    @PutMapping("/update/{sectionId}")
    public ResponseData<?> updateSection(@PathVariable Integer sectionId,
                                         @Valid @RequestBody SectionRequest sectionRequest) {
        try {
            sectionService.updateSection(sectionId, sectionRequest);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Section Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Section could not be updated");
        }
    }

    @DeleteMapping("/delete/{sectionId}")
    public ResponseData<?> deleteSection(@PathVariable Integer sectionId) {
        try {
            sectionService.deleteSection(sectionId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Section Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Section could not be deleted");
        }
    }
}
