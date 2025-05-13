package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserSectionAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserSectionAttemptRepository extends JpaRepository<UserSectionAttempt, Integer> {
    Optional<UserSectionAttempt> findByUserIdAndSectionId(UUID id, Integer sectionId);
}