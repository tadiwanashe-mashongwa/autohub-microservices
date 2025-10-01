package com.autohub.productcatalog.mapper;

import com.autohub.productcatalog.dto.CategoryDto;
import com.autohub.productcatalog.dto.CreateCategoryRequest;
import com.autohub.productcatalog.entity.Category;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T09:03:52+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto toCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        UUID categoryId = null;
        String name = null;
        String description = null;

        categoryId = category.getCategoryId();
        name = category.getName();
        description = category.getDescription();

        CategoryDto categoryDto = new CategoryDto( categoryId, name, description );

        return categoryDto;
    }

    @Override
    public Category toCategory(CreateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( request.name() );
        category.setDescription( request.description() );

        return category;
    }
}
