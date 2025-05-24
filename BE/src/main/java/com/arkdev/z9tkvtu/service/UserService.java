package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.Request.PagingRequest;
import com.arkdev.z9tkvtu.dto.Request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.Request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.Response.UserResponse;
import com.arkdev.z9tkvtu.mapper.UserLoginDataMapper;
import com.arkdev.z9tkvtu.model.PasswordResetToken;
import com.arkdev.z9tkvtu.model.Role;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.repository.PasswordResetTokenRepository;
import com.arkdev.z9tkvtu.repository.RoleRepository;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import com.arkdev.z9tkvtu.util.RoleType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserLoginDataRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserLoginDataMapper dataMapper;
    private final PasswordEncoder passwordEncoder;
    private Role userRole;
    private PasswordResetTokenRepository tokenRepository;
    private JavaMailSender mailSender;

    @PostConstruct
    private void init() {
        userRole = roleRepository.findByRoleType(RoleType.USER)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }
    public UserDetailsService getUserDetailsService() {
        return username -> userRepository.findByUsername(username)
                .or(() -> userRepository.findByEmail(username))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(dataMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        return userRepository.findById(user.getId())
                .map(dataMapper::toUserResponse)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    public void addUser(UserCreationRequest request) throws Exception {
        boolean isSendMail = generateAndSendOtp(request.getEmail());
        if(!isSendMail) {
            throw new BadCredentialsException("Vui lòng kiểm tra lại gmail");
        }
        UserLoginData user = dataMapper.toUserLoginData(request);
        user.setRole(userRole);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        UserLoginData user = userRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        dataMapper.updateUserLoginData(user, request);
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

    @Transactional
    public void disableUser(UUID userId) {
        UserLoginData userLoginData = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userLoginData.setActive(false);
        userRepository.save(userLoginData);
    }

    @Transactional
    public void enableUser(UUID userId) {
        UserLoginData userLoginData = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userLoginData.setActive(true);
        userRepository.save(userLoginData);
    }


    public UserLoginData loadUserByUsernameOrEmail(String usernameOrEmail) {
        UserLoginData user =  userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));
        if (!user.isActive()) {
            throw new DisabledException("Tài khoản đã bị vô hiệu hóa");
        }
        return user;
    }

    public Page<UserLoginData> getAllUser(PagingRequest request){
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<UserLoginData> users = userRepository.findAllByOrderByUsernameAsc(pageable);

        return users.map(user -> {
            user.setPassword(null);
            return user;
        });
    }


    @Transactional
    public boolean generateAndSendOtp(String email) {
        try{
            String otp = String.format("%06d", new Random().nextInt(1000000));
            LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

            tokenRepository.deleteByEmail(email);
            PasswordResetToken token = new PasswordResetToken();
            token.setEmail(email);
            token.setOtp(otp);
            token.setExpiryTime(expiry);
            tokenRepository.save(token);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Xác nhận đăng ký tài khoản");
            message.setText("Mã OTP của bạn là: " + otp + " (Hiệu lực trong 5 phút)");
            mailSender.send(message);
            return true;
        }catch(Exception e){
            return false;
        }

    }

}
