package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Part;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartRepository extends JpaRepository<Part, Integer> {
    Optional<Part> findByPartName(@NotNull(message = "Part name must be not null") String partName);
}