package com.arkdev.z9tkvtu.exception;

import com.arkdev.z9tkvtu.dto.response.ImportError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImportException extends RuntimeException{
    private final String code;
    private final ImportError details;
    public ImportException(String code, ImportError details) {
        this.code = code;
        this.details = details;
    }
}
