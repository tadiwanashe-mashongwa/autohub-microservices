package com.autohub.orderservice.controller;

import com.autohub.orderservice.dto.OrderDto;
import com.autohub.orderservice.dto.PlaceOrderRequest;
import com.autohub.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request,
            @RequestHeader("Authorization") String authHeader) {
        OrderDto createdOrder = orderService.placeOrder(request, authHeader);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
}