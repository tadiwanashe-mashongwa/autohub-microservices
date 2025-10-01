package com.autohub.orderservice.external.client;

import com.autohub.orderservice.external.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

// The 'name' must exactly match the spring.application.name of the target service
@FeignClient(name = "product-catalog-service")
public interface ProductServiceClient {

    // This signature matches the GET /api/v1/products/{id} endpoint in the Product Catalog Service
    @GetMapping("/api/v1/products/{productId}")
    ProductDto getProductById(@PathVariable("productId") UUID productId);
}