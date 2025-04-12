package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.Request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.Response.ResponseData;
import com.arkdev.z9tkvtu.dto.Response.ResponseError;
import com.arkdev.z9tkvtu.model.UserLoginData;
import com.arkdev.z9tkvtu.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/authorities")
    public ResponseData<?> getUserAuthorities() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserLoginData userLoginData = (UserLoginData) auth.getPrincipal();
            return new ResponseData<>(HttpStatus.OK.value(), "Get User Authorities Successfully", userLoginData.getAuthorities());
        } catch (Exception e) {
            return new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get User Authorities Failed");
        }
    }

    @GetMapping("")
    public ResponseData<?> getUsers() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Get All Users Successfully", userService.getUsers());
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get All Users Failed");
        }
    }

    @GetMapping("/{userId}")
    public ResponseData<?> getUserById(@PathVariable("userId") UUID userId) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Get User Successfully", userService.getUser(userId));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get User Failed");
        }
    }

    @PostMapping("")
    public ResponseData<?> addUser(@RequestBody @Valid UserCreationRequest request) {
        try {
            userService.addUser(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "User added successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "User could not be added");
        }
    }

    @PutMapping("/{userId}")
    public ResponseData<?> updateUser(@PathVariable("userId") UUID userId,
                                      @RequestBody @Valid UserUpdateRequest request) {
        try {
            userService.updateUser(userId, request);
            return new ResponseData<>(HttpStatus.OK.value(), "User updated successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "User could not be updated");
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseData<?> deleteUser(@PathVariable("userId") UUID userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "User deleted successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "User could not be deleted");
        }
    }
}
