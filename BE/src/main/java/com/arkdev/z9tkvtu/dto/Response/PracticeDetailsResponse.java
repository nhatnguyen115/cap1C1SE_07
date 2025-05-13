package com.arkdev.z9tkvtu.dto.Response;

import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;
import java.util.List;

public record PracticeDetailsResponse<T>(
    T exam,
    List<PartDetailsResponse<?>> details) implements Serializable {
}
