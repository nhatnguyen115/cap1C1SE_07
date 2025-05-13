package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Response.SectionAttemptDetailsResponse;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.model.UserSectionAttempt;
import com.arkdev.z9tkvtu.repository.UserSectionAttemptRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
class UserSectionAttemptService {
    UserSectionAttemptRepository userSectionAttemptRepository;
    public SectionAttemptDetailsResponse getSectionAttemptDetails(Integer sectionId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        UserSectionAttempt attempt = userSectionAttemptRepository.findByUserIdAndSectionId(user.getId(), sectionId)
                .orElse(null);
        return null;
    }
}
