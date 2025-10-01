package com.autohub.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "inventory_id")
    private UUID inventoryId; // [cite: 342]

    @Column(name = "product_id", nullable = false, unique = true)
    private UUID productId; // [cite: 342]

    @Column(name = "quantity", nullable = false)
    private int quantity; // [cite: 342]

    @Column(name = "reserved", nullable = false)
    private int reserved;// [cite: 342]

    @UpdateTimestamp // Automatically updates this field on every modification
    @Column(name = "last_updated", nullable = false)
    private OffsetDateTime lastUpdated; // [cite: 342]
}