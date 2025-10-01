package com.autohub.paymentservice.dto;

import com.autohub.paymentservice.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record PaymentDto(
        UUID paymentId,
        UUID orderId,
        PaymentStatus status,
        BigDecimal amount,
        String transactionRef,
        OffsetDateTime createdAt
) {}