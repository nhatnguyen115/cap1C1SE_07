package com.arkdev.z9tkvtu.service;

import com.arkdev.z9tkvtu.dto.request.PagingRequest;
import com.arkdev.z9tkvtu.dto.request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.response.UserResponse;
import com.arkdev.z9tkvtu.dto.response.UserVIPResponse;
import com.arkdev.z9tkvtu.mapper.UserLoginDataMapper;
import com.arkdev.z9tkvtu.model.PasswordResetToken;
import com.arkdev.z9tkvtu.model.Role;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.repository.PasswordResetTokenRepository;
import com.arkdev.z9tkvtu.repository.RoleRepository;
import com.arkdev.z9tkvtu.repository.UserLoginDataRepository;
import com.arkdev.z9tkvtu.util.RoleType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
    @Autowired
    private PasswordResetTokenRepository tokenRepository;
    @Autowired
    private JavaMailSender mailSender;


    public static final String URL_VERIFY = "http://localhost:3000/auth/verify?email=";

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

    public UserVIPResponse findAllUsersWithVIPStatus(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserLoginData user = (UserLoginData) auth.getPrincipal();
        return userRepository.findAllUsersWithVIPStatus(user.getId());
    }

    @Transactional
    public void addUser(UserCreationRequest request) {
        UserLoginData userUsername = userRepository.findByUsername(request.getUsername())
                .orElse(null);
        UserLoginData userEmail = userRepository.findByEmail(request.getEmail())
                .orElse(null);
        if (userUsername != null)
            throw new BadCredentialsException("Username already exists");
        if (userEmail != null)
            throw new BadCredentialsException("Email already exists");
        boolean isSendMail = generateAndSendOtp(request.getEmail());
        if(!isSendMail) {
            throw new BadCredentialsException("Vui lòng kiểm tra lại gmail");
        }
        UserLoginData user = dataMapper.toUserLoginData(request);
        user.setRole(userRole);
        user.setActive(false);
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
            message.setText("Mã OTP của bạn là: " + otp + " (Hiệu lực trong 5 phút)\nTruy cập vào: " + URL_VERIFY + email + " để kích hoạt tài khoản");
            mailSender.send(message);
            return true;
        }catch(Exception e){
            return false;
        }

    }

}
