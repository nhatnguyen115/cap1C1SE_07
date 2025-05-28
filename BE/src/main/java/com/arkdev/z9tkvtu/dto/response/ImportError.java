package com.arkdev.z9tkvtu.dto.response;

import java.io.Serializable;

public record ImportError(
        String sheetName,
        Integer rowNumber,
        String fieldName,
        String errorMessage) implements Serializable {
}
