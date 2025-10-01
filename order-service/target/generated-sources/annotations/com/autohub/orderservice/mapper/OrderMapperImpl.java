package com.autohub.orderservice.mapper;

import com.autohub.orderservice.dto.OrderDto;
import com.autohub.orderservice.dto.OrderItemDto;
import com.autohub.orderservice.entity.Order;
import com.autohub.orderservice.entity.OrderItem;
import com.autohub.orderservice.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T10:11:45+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toOrderDto(Order order) {
        if ( order == null ) {
            return null;
        }

        UUID orderId = null;
        UUID userId = null;
        OrderStatus status = null;
        BigDecimal totalAmount = null;
        OffsetDateTime createdAt = null;
        List<OrderItemDto> orderItems = null;

        orderId = order.getOrderId();
        userId = order.getUserId();
        status = order.getStatus();
        totalAmount = order.getTotalAmount();
        createdAt = order.getCreatedAt();
        orderItems = orderItemListToOrderItemDtoList( order.getOrderItems() );

        OrderDto orderDto = new OrderDto( orderId, userId, status, totalAmount, createdAt, orderItems );

        return orderDto;
    }

    protected OrderItemDto orderItemToOrderItemDto(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        UUID orderItemId = null;
        UUID productId = null;
        int quantity = 0;
        BigDecimal price = null;

        orderItemId = orderItem.getOrderItemId();
        productId = orderItem.getProductId();
        quantity = orderItem.getQuantity();
        price = orderItem.getPrice();

        OrderItemDto orderItemDto = new OrderItemDto( orderItemId, productId, quantity, price );

        return orderItemDto;
    }

    protected List<OrderItemDto> orderItemListToOrderItemDtoList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemDto> list1 = new ArrayList<OrderItemDto>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemToOrderItemDto( orderItem ) );
        }

        return list1;
    }
}
