package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.Request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.Response.UserResponse;
import com.arkdev.z9tkvtu.mapper.UserLoginDataMapper;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserLoginDataRepository userRepository;
    UserLoginDataMapper dataMapper;


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

    public void addUser(UserCreationRequest request) {
        UserLoginData user = userRepository.findByUsername(request.getUsername()).orElse(null);
        if (user != null)
            throw new RuntimeException("User already exists");
        user = dataMapper.toUserLoginData(request);
        user.setPasswordHash(new BCryptPasswordEncoder().encode(request.getPassword()));
        userRepository.save(user);
    }

    public void updateUser(UUID userId, UserUpdateRequest request) {
        UserLoginData user = userRepository.findById(userId)
                .map(item -> {
                    dataMapper.updateUserLoginData(item, request);
                    return item;
                }).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        UserLoginData userLoginData = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(userLoginData);
    }
}
