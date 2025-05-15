package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.SignInRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.configuration.JwtProvider;
import com.arkdev.z9tkvtu.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Introspect Error!");
        }
    }

    @GetMapping("/external/callback")
    public ResponseData<?> externalCallback() throws IOException {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            return new ResponseData<>(HttpStatus.OK.value(),
                    "Introspect Successful!",
                    jwtProvider.getToken(auth));
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.BAD_REQUEST.value(), "Get External Info Error!");
        }
    }
}

