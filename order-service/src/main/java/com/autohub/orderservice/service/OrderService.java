package com.autohub.orderservice.service;

import com.autohub.orderservice.config.RabbitMQConfig;
import com.autohub.orderservice.dto.OrderDto;
import com.autohub.orderservice.dto.OrderPlacedEvent;
import com.autohub.orderservice.dto.PlaceOrderRequest;
import com.autohub.orderservice.entity.Order;
import com.autohub.orderservice.entity.OrderItem;
import com.autohub.orderservice.enums.OrderStatus;
import com.autohub.orderservice.external.client.InventoryServiceClient;
import com.autohub.orderservice.external.client.PaymentServiceClient; // Import Payment client
import com.autohub.orderservice.external.client.ProductServiceClient;
import com.autohub.orderservice.external.dto.PaymentRequest; // Import DTOs
import com.autohub.orderservice.external.dto.StockReservationRequest;
import com.autohub.orderservice.mapper.OrderMapper;
import com.autohub.orderservice.repository.OrderRepository;
import com.autohub.orderservice.security.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final PaymentServiceClient paymentServiceClient; // Add Payment client
    private final JwtService jwtService;
    private final OrderMapper orderMapper;
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public OrderDto placeOrder(PlaceOrderRequest request, String jwt) {
        String token = jwt.substring(7);
        UUID userId = UUID.fromString(jwtService.extractClaim(token, claims -> claims.get("userId", String.class)));

        // --- Step 1: Data Fetching and Validation ---
        List<OrderItem> orderItems = request.items().stream().map(itemRequest -> {
            var product = productServiceClient.getProductById(itemRequest.productId());
            var inventory = inventoryServiceClient.getStockLevel(itemRequest.productId());
            if (inventory.quantity() - inventory.reserved() < itemRequest.quantity()) {
                throw new IllegalStateException("Insufficient stock for product ID: " + itemRequest.productId());
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(itemRequest.productId());
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPrice(product.price());
            return orderItem;
        }).collect(Collectors.toList());

        // --- Step 2: Create and Save Initial Order ---
        Order order = new Order();
        order.setUserId(userId);
        order.setStatus(OrderStatus.PENDING);
        orderItems.forEach(item -> item.setOrder(order));
        order.setOrderItems(orderItems);
        BigDecimal totalAmount = orderItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // --- Step 3: Process Payment and Finalize Order ---
        try {
            // Call Payment Service
            PaymentRequest paymentRequest = new PaymentRequest(savedOrder.getOrderId(), savedOrder.getTotalAmount(), "CREDIT_CARD");
            var paymentDto = paymentServiceClient.processPayment(paymentRequest);

            if (!"SUCCESS".equals(paymentDto.status())) {
                throw new IllegalStateException("Payment failed. Order will be cancelled.");
            }

            // If payment is successful, reserve stock
            for (OrderItem item : savedOrder.getOrderItems()) {
                inventoryServiceClient.reserveStock(item.getProductId(), new StockReservationRequest(item.getQuantity()));
            }

            // Update order status to PROCESSING
            savedOrder.setStatus(OrderStatus.PROCESSING);
            orderRepository.save(savedOrder);

            // Publish notification event
            String userEmail = jwtService.extractUsername(token);
            OrderPlacedEvent event = new OrderPlacedEvent(savedOrder.getOrderId(), savedOrder.getUserId(), savedOrder.getTotalAmount(), userEmail);
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, event);

            log.info("Order {} processed successfully.", savedOrder.getOrderId());

        } catch (Exception e) {
            // If anything fails (payment, stock reservation), cancel the order
            log.error("Error processing order {}. Cancelling.", savedOrder.getOrderId(), e);
            savedOrder.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(savedOrder);
            // Optionally re-throw a custom exception to inform the user
            throw new IllegalStateException("Order could not be processed and has been cancelled.", e);
        }

        return orderMapper.toOrderDto(savedOrder);
    }
}