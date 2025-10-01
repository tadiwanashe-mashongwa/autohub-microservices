package com.autohub.paymentservice.mapper;

import com.autohub.paymentservice.dto.PaymentDto;
import com.autohub.paymentservice.entity.Payment;
import com.autohub.paymentservice.enums.PaymentStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-29T07:54:17+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentDto toPaymentDto(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        UUID paymentId = null;
        UUID orderId = null;
        PaymentStatus status = null;
        BigDecimal amount = null;
        String transactionRef = null;
        OffsetDateTime createdAt = null;

        paymentId = payment.getPaymentId();
        orderId = payment.getOrderId();
        status = payment.getStatus();
        amount = payment.getAmount();
        transactionRef = payment.getTransactionRef();
        createdAt = payment.getCreatedAt();

        PaymentDto paymentDto = new PaymentDto( paymentId, orderId, status, amount, transactionRef, createdAt );

        return paymentDto;
    }
}
