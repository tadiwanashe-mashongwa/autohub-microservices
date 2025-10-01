package com.autohub.inventoryservice.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

// The public representation of an inventory record
public record InventoryDto(
        UUID productId,
        int quantity,
        int reserved,
        OffsetDateTime lastUpdated
) {}