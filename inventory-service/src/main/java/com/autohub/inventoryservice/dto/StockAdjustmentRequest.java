package com.autohub.inventoryservice.dto;

import jakarta.validation.constraints.NotNull;

// The request body for adjusting stock
public record StockAdjustmentRequest(
        @NotNull(message = "Adjustment value cannot be null")
        Integer adjustment // Positive to add stock, negative to remove
) {}