package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

    Optional<Module> findByModuleName(String moduleName);

    List<Module> findAllByOrderByModuleName();
}