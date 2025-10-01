package com.autohub.orderservice.external.dto;

import java.util.UUID;

// This record matches the structure of the InventoryDto from the Inventory Service
public record InventoryDto(
        UUID productId,
        int quantity,
        int reserved
) {}