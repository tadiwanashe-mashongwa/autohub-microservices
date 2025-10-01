package com.autohub.productcatalog.repository;

import com.autohub.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    /**
     * Finds all products belonging to a specific category by the category's ID.
     * The method name must match the property path: Product -> category -> categoryId
     */
    List<Product> findByCategoryCategoryId(UUID categoryId); // Corrected method name

    /**
     * Finds all products belonging to a specific category by the category's name.
     * The method name must match the property path: Product -> category -> name
     */
    List<Product> findByCategoryName(String categoryName);
}