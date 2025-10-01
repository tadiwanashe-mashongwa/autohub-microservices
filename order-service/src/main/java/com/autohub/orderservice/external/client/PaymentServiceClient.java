package com.autohub.orderservice.external.client;

import com.autohub.orderservice.external.dto.PaymentDto;
import com.autohub.orderservice.external.dto.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service")
public interface PaymentServiceClient {

    @PostMapping("/api/v1/payments/checkout")
    PaymentDto processPayment(@RequestBody PaymentRequest paymentRequest);
}