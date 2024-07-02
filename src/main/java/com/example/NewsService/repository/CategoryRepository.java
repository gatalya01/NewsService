package com.example.NewsService.repository;

import com.example.NewsService.model.Category;
import com.example.NewsService.service.core.AbstractUniversalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends
        JpaRepository<Category, Integer>,
        JpaSpecificationExecutor<Category>,
        AbstractUniversalService.UniversalRepository<Category> {
}