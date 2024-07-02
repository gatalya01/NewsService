package com.example.NewsService.dto;

import com.example.NewsService.dto.news.NewsResponseForList;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryResponse {
    private int id;
    private String name;
    private List<NewsResponseForList> news = new ArrayList<>();

    public CategoryResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }
}