package com.autohub.orderservice.external.client;

import com.autohub.orderservice.external.dto.InventoryDto;
import com.autohub.orderservice.external.dto.StockReservationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    // This signature matches the GET /api/v1/inventory/{productId} endpoint in the Inventory Service
    @GetMapping("/api/v1/inventory/{productId}")
    InventoryDto getStockLevel(@PathVariable("productId") UUID productId);
    // ... inside the InventoryServiceClient interface ...

    @PostMapping("/api/v1/inventory/{productId}/reserve")
    void reserveStock(@PathVariable("productId") UUID productId, StockReservationRequest request);
}