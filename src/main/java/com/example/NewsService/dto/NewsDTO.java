package com.example.NewsService.dto;
import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDTO {
    private int newsId;
    private int id;
    private String title;
    private String content;
    private int userId;
    private int categoryId;
    private Instant createdAt;
    private Instant updatedAt;
}