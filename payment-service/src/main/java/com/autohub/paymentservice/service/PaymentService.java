package com.autohub.paymentservice.service;

import com.autohub.paymentservice.dto.PaymentDto;
import com.autohub.paymentservice.dto.PaymentRequest;
import com.autohub.paymentservice.entity.Payment;
import com.autohub.paymentservice.enums.PaymentStatus;
import com.autohub.paymentservice.mapper.PaymentMapper;
import com.autohub.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Transactional
    public PaymentDto processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setOrderId(request.orderId());
        payment.setAmount(request.amount());
        payment.setMethod(request.paymentMethod());
        payment.setStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);

        // --- MOCK PAYMENT LOGIC ---
        // Simulate success or failure based on the amount.
        if (request.amount().toString().endsWith(".99")) {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setTransactionRef("FAIL_" + UUID.randomUUID());
        } else {
            payment.setStatus(PaymentStatus.SUCCESS);
            payment.setTransactionRef("SUCCESS_" + UUID.randomUUID());
        }

        Payment processedPayment = paymentRepository.save(payment);
        return paymentMapper.toPaymentDto(processedPayment);
    }
}