package com.autohub.productcatalog.service;

import com.autohub.productcatalog.dto.CategoryDto;
import com.autohub.productcatalog.dto.CreateCategoryRequest;
import com.autohub.productcatalog.entity.Category;
import com.autohub.productcatalog.mapper.CategoryMapper;
import com.autohub.productcatalog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CreateCategoryRequest request) {
        Category newCategory = categoryMapper.toCategory(request);
        Category savedCategory = categoryRepository.save(newCategory);
        return categoryMapper.toCategoryDto(savedCategory);
    }
}