package com.autohub.productcatalog.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(
        @NotBlank(message = "Category name cannot be blank")
        String name,
        String description
) {
}