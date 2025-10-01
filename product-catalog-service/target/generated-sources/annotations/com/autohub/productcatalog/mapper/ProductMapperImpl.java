package com.autohub.productcatalog.mapper;

import com.autohub.productcatalog.dto.CategoryDto;
import com.autohub.productcatalog.dto.CreateProductRequest;
import com.autohub.productcatalog.dto.ProductDto;
import com.autohub.productcatalog.entity.Product;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T09:03:51+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDto toProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        UUID productId = null;
        String name = null;
        String description = null;
        BigDecimal price = null;
        CategoryDto category = null;
        String imageUrl = null;
        OffsetDateTime createdAt = null;

        productId = product.getProductId();
        name = product.getName();
        description = product.getDescription();
        price = product.getPrice();
        category = categoryMapper.toCategoryDto( product.getCategory() );
        imageUrl = product.getImageUrl();
        createdAt = product.getCreatedAt();

        ProductDto productDto = new ProductDto( productId, name, description, price, category, imageUrl, createdAt );

        return productDto;
    }

    @Override
    public Product toProduct(CreateProductRequest createProductRequest) {
        if ( createProductRequest == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( createProductRequest.name() );
        product.setDescription( createProductRequest.description() );
        product.setPrice( createProductRequest.price() );
        product.setImageUrl( createProductRequest.imageUrl() );

        return product;
    }
}
