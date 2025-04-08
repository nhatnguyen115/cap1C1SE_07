package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Test;
import com.arkdev.z9tkvtu.util.TestType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TestRepository extends JpaRepository<Test, Integer> {
    boolean existsByTestType(@NotNull(message = "Test type must be not null") TestType testType);

    Optional<Test> findByTestType(@NotNull(message = "Test type must be not null") TestType testType);
}