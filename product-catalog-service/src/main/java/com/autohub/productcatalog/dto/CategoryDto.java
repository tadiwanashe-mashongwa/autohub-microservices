package com.autohub.productcatalog.dto;

import java.util.UUID;

public record CategoryDto(UUID categoryId, String name, String description) {
}