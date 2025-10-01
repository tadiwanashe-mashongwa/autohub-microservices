package com.autohub.orderservice.repository;

import com.autohub.orderservice.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    // We don't need custom methods here for now,
    // as we will typically access items through their parent Order.
}