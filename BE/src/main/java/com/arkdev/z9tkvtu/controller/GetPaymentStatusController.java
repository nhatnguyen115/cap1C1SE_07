package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.StatusRequest;
import com.arkdev.z9tkvtu.service.GetPaymentStatusService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GetPaymentStatusController {
    GetPaymentStatusService getPaymentStatusService;
    @PostMapping("/api/v1/get-status")
    public ResponseEntity<Map<String, Object>> getStatus(HttpServletRequest request,
                                                         @RequestBody StatusRequest statusRequest) throws IOException, IOException {

        Map<String, Object> result = this.getPaymentStatusService.getStatus(request, statusRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
