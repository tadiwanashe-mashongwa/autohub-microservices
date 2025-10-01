package com.autohub.orderservice.repository;

import com.autohub.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    /**
     * Finds all orders placed by a specific user.
     *
     * @param userId The UUID of the user.
     * @return A list of orders for the given user.
     */
    List<Order> findByUserId(UUID userId);
}