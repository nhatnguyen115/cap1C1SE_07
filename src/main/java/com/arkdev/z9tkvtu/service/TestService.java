package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.TestRequest;
import com.arkdev.z9tkvtu.dto.Response.TestResponse;
import com.arkdev.z9tkvtu.mapper.TestMapper;
import com.arkdev.z9tkvtu.model.Test;
import com.arkdev.z9tkvtu.repository.TestRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestService {
    TestRepository testRepository;
    TestMapper testMapper;

    public List<TestResponse> getTests() {
        return testRepository.findAll()
                .stream()
                .map(testMapper::toTestResponse)
                .toList();
    }

    public TestResponse getTest(Integer testId) {
        return testRepository.findById(testId)
                .map(testMapper::toTestResponse)
                .orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public void addTest(TestRequest request) {
        Test test = testRepository.findByTestType(request.getTestType())
                .orElse(null);
        if (test != null)
            throw new IllegalArgumentException("Test already exists");
        test = testMapper.toTest(request);
        testRepository.save(test);
    }

    public void updateTest(Integer testId, TestRequest request) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        testMapper.updateTest(test, request);
        testRepository.save(test);
    }

    public void deleteTest(Integer testId) {
        Test test = testRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Test not found"));
        testRepository.delete(test);
    }
}

