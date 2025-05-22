package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.ResetPasswordRequest;
import com.arkdev.z9tkvtu.dto.Request.SignInRequest;
import com.arkdev.z9tkvtu.dto.Request.VerifyOtpRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.configuration.JwtProvider;
import com.arkdev.z9tkvtu.dto.Response.TokenResponse;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.service.PasswordResetService;
import com.arkdev.z9tkvtu.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/auth")
@Validated
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    JwtProvider jwtProvider;
    UserService userService;
    AuthenticationManager manager;
    PasswordResetService service;

    @Autowired
    private SecurityContextRepository securityContextRepository;
    @PostMapping("/introspect")
    public ResponseData<?> introspect(@RequestBody @Valid SignInRequest request) {
        try {
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
            if (auth.isAuthenticated()) {
                return new ResponseData<>(HttpStatus.OK.value(),
                        "Introspect Successful!",
                        jwtProvider.getToken(auth));
            } else {
                return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(),
                        "Introspect Failed!");
            }
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/external/callback")
    public ResponseData<?> externalCallback(@RequestHeader("Authorization") String authHeader, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid Authorization header!");
            }

            String token = authHeader.substring(7);
            String usernameOrEmail = jwtProvider.extractUsername(token);

            // Gọi hàm mới trong UserService
            UserLoginData userDetails = userService.loadUserByUsernameOrEmail(usernameOrEmail);
            if (!userDetails.isEnabled()) {
                return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Tài khoản của bạn đã bị vô hiệu hóa");
            }
            if (!jwtProvider.validateToken(token, userDetails)) {
                return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired token!");
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );
            if (authentication.isAuthenticated()) {
                SecurityContext sc = SecurityContextHolder.getContextHolderStrategy().createEmptyContext();
                sc.setAuthentication(authentication);
                securityContextRepository.saveContext(sc, request, response);
            }
            TokenResponse tokenResponse = jwtProvider.getToken(authentication);

            return new ResponseData<>(HttpStatus.OK.value(), "Introspect Successful!", tokenResponse);

        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Get External Info Error!");
        }
    }

    @PostMapping("/request")
    public ResponseData<?> requestReset(@RequestParam String email) {
        try {
            service.generateAndSendOtp(email);
            return new ResponseData<>(200, "OTP đã được gửi về email");
        } catch (Exception e) {
            log.error("Error in requestReset:", e);
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Lỗi khi gửi OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseData<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
        try {
            boolean valid = service.verifyOtp(request.getEmail(), request.getOtp());
            return valid
                    ? new ResponseData<>(200, "OTP hợp lệ")
                    : new ResponseData<>(400, "OTP sai hoặc đã hết hạn");
        } catch (Exception e) {
            log.error("Error in verifyOtp:", e);
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Lỗi khi xác thực OTP: " + e.getMessage());
        }
    }

    @PostMapping("/reset")
    public ResponseData<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            service.resetPassword(request.getEmail(), request.getNewPassword());
            return new ResponseData<>(200, "Mật khẩu đã được cập nhật");
        } catch (Exception e) {
            log.error("Error in resetPassword:", e);
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Lỗi khi cập nhật mật khẩu: " + e.getMessage());
        }
    }


}

