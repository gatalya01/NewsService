package com.example.NewsService.dto;

import com.example.NewsService.dto.category.CategoryResponseForList;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListResponse {
    private List<CategoryResponseForList> categories = new ArrayList<>();
}