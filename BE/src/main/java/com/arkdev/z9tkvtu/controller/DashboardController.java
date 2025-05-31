package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.request.PagingRequest;
import com.arkdev.z9tkvtu.dto.request.UserCreationRequest;
import com.arkdev.z9tkvtu.dto.request.UserUpdateRequest;
import com.arkdev.z9tkvtu.dto.response.DashboardResponse;
import com.arkdev.z9tkvtu.dto.response.DashboardSummaryResponse;
import com.arkdev.z9tkvtu.dto.response.ResponseData;
import com.arkdev.z9tkvtu.dto.response.ResponseError;
import com.arkdev.z9tkvtu.service.DashboardService;
import com.arkdev.z9tkvtu.service.UserService;
import com.arkdev.z9tkvtu.validator.UserCreationValidator;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dashboard")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class DashboardController {

    DashboardService dashboardService;

    @GetMapping
    public ResponseData<DashboardResponse> getDashboardData(@RequestParam(defaultValue = "2025") int year) {
        try {
            DashboardResponse response = dashboardService.getDashboardData(year);
            return new ResponseData<>(200,"Get Dashboard data success",response);
        }catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Get Dashboard data error!");
        }
    }

    @GetMapping("/summary")
    public ResponseData<DashboardSummaryResponse> getDashboardSummary() {
        try {

            DashboardSummaryResponse response = dashboardService.getDashboardSummary();
            return new ResponseData<>(200,"Get Dashboard summary data success",response);
        }catch (Exception e) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Get Dashboard summary data error!");
        }
    }
}
