package com.autohub.productcatalog.mapper;

import com.autohub.productcatalog.dto.CategoryDto;
import com.autohub.productcatalog.dto.CreateCategoryRequest;
import com.autohub.productcatalog.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toCategoryDto(Category category);

    @Mapping(target = "categoryId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Category toCategory(CreateCategoryRequest request);
}