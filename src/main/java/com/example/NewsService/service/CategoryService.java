package com.example.NewsService.service;

import com.example.NewsService.dto.CategoryDTO;
import com.example.NewsService.mapper.CategoryMapper;
import com.example.NewsService.model.Category;
import com.example.NewsService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDTO(savedCategory);
    }

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDTOs(categories);
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toDTO);
    }

    public void updateCategory(CategoryDTO newCategoryDTO) {
        Category existingCategory = categoryRepository.findById(newCategoryDTO.getId()).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Категория с указанным id не найдена");
        }
        existingCategory.setName(newCategoryDTO.getName());
        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long categoryId) {
        Category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory == null) {
            throw new IllegalArgumentException("Категория с указанным id не найдена");
        }
        categoryRepository.deleteById(categoryId);
    }
}