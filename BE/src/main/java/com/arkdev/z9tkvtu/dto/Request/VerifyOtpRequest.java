package com.arkdev.z9tkvtu.dto.Request;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}