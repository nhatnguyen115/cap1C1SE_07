package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserAnswer;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
    UserAnswer findByQuestionIdAndAttemptId(@Param("questionId") Integer questionId,
                                            @Param("attemptId") Integer attemptId);
}