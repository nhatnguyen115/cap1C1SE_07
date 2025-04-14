package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.Request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.Response.UserResponse;
import com.arkdev.z9tkvtu.mapper.UserLoginDataMapper;
import com.arkdev.z9tkvtu.model.Role;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.repository.RoleRepository;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import com.arkdev.z9tkvtu.util.RoleType;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserLoginDataRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserLoginDataMapper dataMapper;
    private final PasswordEncoder passwordEncoder;
    private Role userRole;

    @PostConstruct
    private void init() {
        userRole = roleRepository.findByRoleType(RoleType.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    public UserDetailsService getUserDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(dataMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser(UUID userId) {
        return userRepository.findById(userId)
                .map(dataMapper::toUserResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void addUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");
        UserLoginData user = dataMapper.toUserLoginData(request);
        user.setRole(userRole);
        user.setActive(true);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UUID userId, UserUpdateRequest request) {
        UserLoginData user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        dataMapper.updateUserLoginData(user, request);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        UserLoginData userLoginData = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userLoginData);
    }
}
