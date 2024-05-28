package com.example.NewsService.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewsDTO {

    private Long id;
    private String title;
    private String content;
    private Long categoryId;
    private Long authorId;
    private int commentCount;
}