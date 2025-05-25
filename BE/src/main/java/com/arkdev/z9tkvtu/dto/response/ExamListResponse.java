package com.arkdev.z9tkvtu.dto.Response;

import com.arkdev.z9tkvtu.util.DifficultyLevel;

import java.io.Serializable;

public record ExamListResponse (
        Integer id,
        String examName,
        Integer totalScore,
        Integer duration,
        Integer questionCount,
        Integer students,
        DifficultyLevel level) implements Serializable {
}
