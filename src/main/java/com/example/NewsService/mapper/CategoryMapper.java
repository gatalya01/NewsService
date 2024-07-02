package com.example.NewsService.mapper;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.CategoryResponse;
import com.example.NewsService.dto.category.CategoryResponseForList;
import com.example.NewsService.dto.CategoryListResponse;
import com.example.NewsService.dto.category.CategoryUpsertRequest;
import com.example.NewsService.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {NewsMapper.class})
public interface CategoryMapper {
    Category requestToCategory(CategoryUpsertRequest request);

    CategoryResponse categoryToCategoryResponse(Category category);

    @Loggable
    default CategoryResponseForList categoryToCategoryForList(Category category) {
        return new CategoryResponseForList(
                category.getId(),
                category.getName(),
                category.getNews().size()
        );
    }

    List<CategoryResponseForList> categoryListToListOfCategoryResponse(List<Category> categories);

    @Loggable
    default CategoryListResponse categoryListToCategoryListResponse(List<Category> categories) {
        return new CategoryListResponse(this.categoryListToListOfCategoryResponse(categories));
    }
}