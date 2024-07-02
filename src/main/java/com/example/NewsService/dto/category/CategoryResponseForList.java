package com.example.NewsService.dto.category;

import lombok.*;

@Data
@AllArgsConstructor
public class CategoryResponseForList {
    private int id;
    private String name;
    private int newsCount;
}