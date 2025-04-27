package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
}