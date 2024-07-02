package com.example.NewsService.dto.news;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsFilter {
    private Integer pageSize;
    private Integer pageNumber;
    private Integer userId;
    private String userName;
    private Integer categoryId;
    private String categoryName;

    public NewsFilter(Integer pageSize, Integer pageNumber) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }
}