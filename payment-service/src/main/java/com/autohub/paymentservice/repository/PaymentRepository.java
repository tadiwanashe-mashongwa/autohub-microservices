package com.autohub.paymentservice.repository;

import com.autohub.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    /**
     * Finds a payment record by the associated order's unique ID.
     *
     * @param orderId The UUID of the order.
     * @return An Optional containing the payment record if found.
     */
    Optional<Payment> findByOrderId(UUID orderId);
}