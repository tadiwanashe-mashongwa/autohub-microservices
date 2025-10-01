package com.autohub.paymentservice.mapper;

import com.autohub.paymentservice.dto.PaymentDto;
import com.autohub.paymentservice.entity.Payment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentDto toPaymentDto(Payment payment);
}