package com.autohub.orderservice.dto;
import com.autohub.orderservice.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
public record OrderDto(
        UUID orderId, UUID userId, OrderStatus status,
        BigDecimal totalAmount, OffsetDateTime createdAt, List<OrderItemDto> orderItems
) {}