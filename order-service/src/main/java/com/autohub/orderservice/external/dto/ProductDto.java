package com.autohub.orderservice.external.dto;

import java.math.BigDecimal;
import java.util.UUID;

// This record matches the structure of the ProductDto from the Product Catalog Service
public record ProductDto(
        UUID productId,
        String name,
        BigDecimal price
) {}