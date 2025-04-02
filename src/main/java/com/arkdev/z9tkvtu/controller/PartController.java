package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.MediaRequest;
import com.arkdev.z9tkvtu.dto.Request.PartRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.PartService;
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
@RequestMapping("/part")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PartController {
    PartService partService;

    @GetMapping("")
    public ResponseData<?> getParts(@RequestParam Integer selectedId,
                                    @RequestParam boolean checked,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        try {
            if (checked) {
                return new ResponseData<>(HttpStatus.OK.value(), "Get Parts To Section Successfully",
                        Pagination.paginate(partService.getPartsToSection(selectedId), PageRequest.of(page, size)));
            } else {
                return new ResponseData<>(HttpStatus.OK.value(), "Get Parts To Exam Successfully",
                        Pagination.paginate(partService.getPartsToExam(selectedId), PageRequest.of(page, size)));
            }
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Parts Failed");
        }
    }

    @GetMapping("/{partId}")
    public ResponseData<?> getPart(@PathVariable Integer partId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "Get Part Successfully",
                    partService.getPart(partId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get Part Failed");
        }
    }

    @PostMapping("/add")
    public ResponseData<?> addPart(@RequestParam Integer selectedId ,
                                   @RequestParam boolean checked,
                                   @Valid @RequestBody PartRequest request) {
        try {
            if (checked) {
                partService.addPartToSection(selectedId, request);
                return new ResponseData<>(HttpStatus.CREATED.value(), "Add Part To Section Successfully");
            } else {
                partService.addPartToExam(selectedId, request);
                return new ResponseData<>(HttpStatus.CREATED.value(), "Add Part To Exam Successfully");
            }
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Part could not be added");
        }
    }

    @PutMapping("/update/{partId}")
    public ResponseData<?> updatePart(@PathVariable Integer partId, @RequestBody PartRequest request) {
        try {
            partService.updatePart(partId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Update Part Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Part could not be updated");
        }
    }

    @DeleteMapping("/delete/{partId}")
    public ResponseData<?> deletePart(@PathVariable Integer partId) {
        try {
            partService.deletePart(partId);
            return new ResponseData<>(HttpStatus.OK.value(), "Delete Part Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Part could not be deleted");
        }
    }

    @PutMapping("/add/media/{partId}")
    public ResponseData<?> addMediaToPart(@PathVariable Integer partId,
                                          @RequestBody MediaRequest request) {
        try {
            partService.addMediaToPart(partId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "Add Media To Part Successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Media could not be added");
        }
    }
}
