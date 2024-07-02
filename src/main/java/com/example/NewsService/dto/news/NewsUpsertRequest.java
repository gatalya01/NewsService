package com.example.NewsService.dto.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsUpsertRequest {
    private String title;

    private String content;

    private int userId;
    private int categoryId;
}