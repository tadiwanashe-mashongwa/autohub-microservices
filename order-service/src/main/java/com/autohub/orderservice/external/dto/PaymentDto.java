package com.autohub.orderservice.external.dto;

import java.util.UUID;

// We only need the 'status' from the response for our logic
public record PaymentDto(UUID paymentId, String status) {}