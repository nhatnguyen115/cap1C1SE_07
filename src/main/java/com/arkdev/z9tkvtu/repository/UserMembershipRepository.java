package com.arkdev.z9tkvtu.repository;

import com.arkdev.z9tkvtu.model.UserMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMembershipRepository extends JpaRepository<UserMembership, Integer> {
}