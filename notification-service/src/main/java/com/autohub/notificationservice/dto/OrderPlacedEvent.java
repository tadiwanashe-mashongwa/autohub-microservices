package com.autohub.notificationservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

// This must match the structure of the event sent by the Order Service
public record OrderPlacedEvent(
        UUID orderId,
        UUID userId,
        BigDecimal totalAmount,
        String customerEmail
) {}