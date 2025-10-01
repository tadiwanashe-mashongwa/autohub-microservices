package com.autohub.productcatalog.service;

import com.autohub.productcatalog.dto.CreateProductRequest;
import com.autohub.productcatalog.dto.ProductDto;
import com.autohub.productcatalog.entity.Product;
import com.autohub.productcatalog.mapper.ProductMapper;
import com.autohub.productcatalog.repository.CategoryRepository; // Import CategoryRepository
import com.autohub.productcatalog.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException; // Import a standard exception
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository; // Add repository
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository; // Add to constructor
        this.productMapper = productMapper;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductDto)
                .collect(Collectors.toList());
    }
    // ... inside the ProductService class

    public ProductDto getProductById(UUID productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductDto)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Product not found with ID: " + productId));
    }

    public ProductDto createProduct(CreateProductRequest request) {
        // Find the category by its ID, or throw an error if it doesn't exist
        var category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + request.categoryId()));

        Product newProduct = productMapper.toProduct(request);
        newProduct.setCategory(category); // Set the category object on the product

        Product savedProduct = productRepository.save(newProduct);
        return productMapper.toProductDto(savedProduct);
    }
}