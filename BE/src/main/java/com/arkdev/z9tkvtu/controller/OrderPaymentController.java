package com.arkdev.z9tkvtu.controller;

import com.arkdev.z9tkvtu.dto.Request.OrderRequest;
import com.arkdev.z9tkvtu.service.OrderPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class OrderPaymentController {
    OrderPaymentService orderPaymentService;
    @PostMapping("/api/v1/create-order")
    public ResponseEntity<Map<String, Object>> createOrderPayment(HttpServletRequest request,
                                                                  @RequestBody OrderRequest orderRequest) throws UnsupportedEncodingException {

        Map<String, Object> result = this.orderPaymentService.createOrder(request, orderRequest);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
