package com.autohub.productcatalog.mapper;

import com.autohub.productcatalog.dto.CreateProductRequest;
import com.autohub.productcatalog.dto.ProductDto;
import com.autohub.productcatalog.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

// Add uses = CategoryMapper.class to the annotation
@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    @Mapping(target = "productId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "category", ignore = true) // Ignore category here, as we set it manually in the service
    Product toProduct(CreateProductRequest createProductRequest);
}