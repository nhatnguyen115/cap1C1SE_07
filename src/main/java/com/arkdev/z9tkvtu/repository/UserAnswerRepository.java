package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.Response.AnswerResponse;
import com.arkdev.z9tkvtu.dto.Response.ResultPartResponse;
import com.arkdev.z9tkvtu.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserAnswerRepository extends JpaRepository<UserAnswer, Integer> {
}