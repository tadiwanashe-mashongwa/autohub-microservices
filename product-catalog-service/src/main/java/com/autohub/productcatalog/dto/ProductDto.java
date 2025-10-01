package com.autohub.productcatalog.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ProductDto(
        UUID productId,
        String name,
        String description,
        BigDecimal price,
        CategoryDto category, // Changed from String to CategoryDto
        String imageUrl,
        OffsetDateTime createdAt
) {}