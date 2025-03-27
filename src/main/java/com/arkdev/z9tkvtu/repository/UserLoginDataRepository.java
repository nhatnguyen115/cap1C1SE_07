package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserLoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserLoginDataRepository extends JpaRepository<UserLoginData, UUID> {
  Optional<UserLoginData> findByUsername(String username);
}