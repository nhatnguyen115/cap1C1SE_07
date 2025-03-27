package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(@NotNull(message = "Role name must be not null") @Size(max = 10) String roleName);
}