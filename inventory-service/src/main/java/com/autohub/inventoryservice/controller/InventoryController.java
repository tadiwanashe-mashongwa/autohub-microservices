package com.autohub.inventoryservice.controller;

import com.autohub.inventoryservice.dto.InventoryDto;
import com.autohub.inventoryservice.dto.StockAdjustmentRequest;
import com.autohub.inventoryservice.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryDto> getStockLevel(@PathVariable UUID productId) {
        return ResponseEntity.ok(inventoryService.getInventoryByProductId(productId));
    }
    // ... inside the InventoryController class ...

    @PostMapping("/{productId}/reserve")
    public ResponseEntity<InventoryDto> reserveStock(
            @PathVariable UUID productId,
            @Valid @RequestBody com.autohub.inventoryservice.dto.ReserveStockRequest request) {
        return ResponseEntity.ok(inventoryService.reserveStock(productId, request.quantity()));
    }

    @PostMapping("/{productId}/adjust")
    public ResponseEntity<InventoryDto> adjustStockLevel(
            @PathVariable UUID productId,
            @Valid @RequestBody StockAdjustmentRequest request) {
        return ResponseEntity.ok(inventoryService.adjustStock(productId, request.adjustment()));
    }
}