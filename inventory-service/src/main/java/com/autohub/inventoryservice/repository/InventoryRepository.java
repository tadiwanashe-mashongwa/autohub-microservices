package com.autohub.inventoryservice.repository;

import com.autohub.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

    /**
     * Finds an inventory record by the product's unique ID.
     * This will be the main method we use to check stock for a given product.
     *
     * @param productId The UUID of the product.
     * @return An Optional containing the inventory record if found.
     */
    Optional<Inventory> findByProductId(UUID productId);
}