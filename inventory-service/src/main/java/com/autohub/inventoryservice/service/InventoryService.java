package com.autohub.inventoryservice.service;

import com.autohub.inventoryservice.dto.InventoryDto;
import com.autohub.inventoryservice.entity.Inventory;
import com.autohub.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public InventoryDto getInventoryByProductId(UUID productId) {
        // Find inventory or return a default (0 stock) if none exists
        return inventoryRepository.findByProductId(productId)
                .map(this::toDto)
                .orElse(new InventoryDto(productId, 0, 0, null));
    }

    @Transactional
    public InventoryDto adjustStock(UUID productId, int adjustment) {
        // Find the existing record or create a new one if it doesn't exist
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseGet(() -> createNewInventory(productId));

        int newQuantity = inventory.getQuantity() + adjustment;

        // Business rule: Do not allow stock to go below zero
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }

        inventory.setQuantity(newQuantity);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return toDto(savedInventory);
    }

    // --- Private Helper Methods ---

    private Inventory createNewInventory(UUID productId) {
        Inventory inventory = new Inventory();
        inventory.setProductId(productId);
        inventory.setQuantity(0);
        inventory.setReserved(0);
        return inventory;
    }
    // ... inside the InventoryService class ...

    @Transactional
    public InventoryDto reserveStock(UUID productId, int quantityToReserve) {
        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Inventory not found for product ID: " + productId));

        int availableStock = inventory.getQuantity() - inventory.getReserved();
        if (availableStock < quantityToReserve) {
            throw new IllegalStateException("Insufficient available stock to reserve.");
        }

        inventory.setReserved(inventory.getReserved() + quantityToReserve);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return toDto(savedInventory);
    }

    private InventoryDto toDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getReserved(),
                inventory.getLastUpdated()
        );
    }
}