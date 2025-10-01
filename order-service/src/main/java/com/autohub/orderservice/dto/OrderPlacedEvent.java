package com.autohub.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

// This represents the message we will send to RabbitMQ
public record OrderPlacedEvent(
        UUID orderId,
        UUID userId,
        BigDecimal totalAmount,
        String customerEmail // We will add logic to fetch this later
) {}