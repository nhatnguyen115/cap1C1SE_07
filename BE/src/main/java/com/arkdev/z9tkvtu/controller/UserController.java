package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.request.PagingRequest;
import com.arkdev.z9tkvtu.dto.request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.response.ResponseData;
import com.arkdev.z9tkvtu.dto.response.ResponseError;
import com.arkdev.z9tkvtu.service.UserService;
import com.arkdev.z9tkvtu.validator.UserCreationValidator;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/list")
    public ResponseData<?> getUsers() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Get All Users Successfully", userService.getUsers());
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get All Users Failed");
        }
    }

    @GetMapping("/paging")
    public ResponseData<?> getUsersPaging(@ModelAttribute PagingRequest pagingRequest) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Get All Users Successfully", userService.getAllUser(pagingRequest));
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get All Users Failed");
        }
    }

    @GetMapping("")
    public ResponseData<?> getUserById() {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Get User Successfully", userService.getUser());
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Get User Failed");
        }
    }

    @PostMapping("")
    public ResponseData<?> addUser(@RequestBody @Valid UserCreationRequest request) {
        try {
            List<String> validationErrors = UserCreationValidator.validate(request);

            if (!validationErrors.isEmpty()) {
                throw new IllegalArgumentException(String.join("; ", validationErrors));
            }

            userService.addUser(request);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Đăng ký thành công, vui lòng xác minh tài khoản trong Email");
        }catch (BadCredentialsException be){
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), be.getMessage());
        }
        catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseData<?> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        try {
            userService.updateUser(request);
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

    @PutMapping("disable/{userId}")
    public ResponseData<?> disableUser(@PathVariable("userId") UUID userId) {
        try {
            userService.disableUser(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "User deleted successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "User could not be deleted");
        }
    }

    @PutMapping("enable/{userId}")
    public ResponseData<?> enableUser(@PathVariable("userId") UUID userId) {
        try {
            userService.enableUser(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "User enabled successfully");
        } catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "User could not be enabled");
        }
    }
}
