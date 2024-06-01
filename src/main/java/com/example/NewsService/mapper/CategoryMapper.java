package com.example.NewsService.mapper;

import com.example.NewsService.dto.CategoryDTO;
import com.example.NewsService.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO toDto(Category category);

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    default Category createCategoryFromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getName());
    }
}