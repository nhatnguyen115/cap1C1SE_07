package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.dto.Response.TestHistoryResponse;
import com.arkdev.z9tkvtu.model.UserAccount;
import com.arkdev.z9tkvtu.model.UserTestAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTestAttemptRepository extends JpaRepository<UserTestAttempt, Integer> {
    List<UserTestAttempt> findByUser(UserAccount user);
}