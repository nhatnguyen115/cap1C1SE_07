package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.SignInRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.service.JwtService;
import com.arkdev.z9tkvtu.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    JwtService jwtService;
    AuthenticationManager manager;

    @PostMapping("/introspect")
    public ResponseData<?> introspect(@RequestBody SignInRequest request) {
        try {
            Authentication auth = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),
                            request.getPassword()));
            if (auth.isAuthenticated()) {
                return new ResponseData<>(HttpStatus.OK.value(),
                        "Introspect Successful!",
                        jwtService.generateToken(request.getUsername()));
            } else {
                return new ResponseData<>(HttpStatus.UNAUTHORIZED.value(),
                        "Introspect Failed!");
            }
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Introspect Error!");
        }
    }
}

