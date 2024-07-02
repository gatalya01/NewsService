package com.example.NewsService.service.impl;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.category.CategoryFilter;
import com.example.NewsService.model.Category;
import com.example.NewsService.repository.CategoryRepository;
import com.example.NewsService.service.CategoryService;
import com.example.NewsService.service.core.AbstractUniversalService;
import com.example.NewsService.util.ErrorMsg;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends AbstractUniversalService<Category, CategoryFilter> implements CategoryService {
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository, ErrorMsg.CATEGORY_BY_ID_NOT_FOUND);
    }

    @Loggable
    @Override
    public List<Category> findAllByFilter(CategoryFilter filter) {
        return super.repository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }
}
