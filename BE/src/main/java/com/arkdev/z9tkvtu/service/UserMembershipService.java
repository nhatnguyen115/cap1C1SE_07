package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.repository.MembershipPlanRepository;
import com.arkdev.z9tkvtu.repository.UserMembershipRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class UserMembershipService {
    MembershipPlanRepository membershipPlanRepository;
    UserMembershipRepository userMembershipRepository;

//    public void reg
}
