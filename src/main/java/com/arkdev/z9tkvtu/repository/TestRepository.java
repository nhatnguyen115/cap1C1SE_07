package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Test;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {
    Optional<Test> findByTestType(@NotNull(message = "Test type must be not null") String testType);
    List<Test> findAllByOrderByTestType();
}