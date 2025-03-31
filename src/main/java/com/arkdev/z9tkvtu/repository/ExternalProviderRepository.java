package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.ExternalProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalProviderRepository extends JpaRepository<ExternalProvider, Integer> {
}