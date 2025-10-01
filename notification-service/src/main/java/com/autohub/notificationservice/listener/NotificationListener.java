package com.autohub.notificationservice.listener;

import com.autohub.notificationservice.dto.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j // Lombok annotation for easy logging
public class NotificationListener {

    @RabbitListener(queues = "notification-queue")
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        // For now, we will just log the message to the console.
        // In a real application, this is where you would put the logic to send an email.
        log.info("Received notification for Order ID: {}", event.orderId());
        log.info("Simulating sending confirmation email to: {}", event.customerEmail());

        // Example: emailService.sendOrderConfirmation(event);
    }
}