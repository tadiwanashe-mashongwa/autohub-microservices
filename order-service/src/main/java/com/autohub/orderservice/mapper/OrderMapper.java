package com.autohub.orderservice.mapper;
import com.autohub.orderservice.dto.OrderDto;
import com.autohub.orderservice.entity.Order;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toOrderDto(Order order);
}