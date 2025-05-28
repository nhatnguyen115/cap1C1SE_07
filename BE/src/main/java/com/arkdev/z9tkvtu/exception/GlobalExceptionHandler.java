package com.arkdev.z9tkvtu.exception;

import com.arkdev.z9tkvtu.dto.response.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ImportException.class)
    public ResponseData<?> handleImportException(ImportException ex) {
        return new ResponseData<>(HttpStatus.BAD_REQUEST.value(),
                ex.getCode(),
                ex.getDetails());
    }
}
