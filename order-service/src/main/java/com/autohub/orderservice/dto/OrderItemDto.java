package com.autohub.orderservice.dto;
import java.math.BigDecimal;
import java.util.UUID;
public record OrderItemDto(UUID orderItemId, UUID productId, int quantity, BigDecimal price) {}