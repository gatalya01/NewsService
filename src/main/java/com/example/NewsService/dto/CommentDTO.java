package com.example.NewsService.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private int id;
    private String content;
    private int newsId;
    private int userId;
    private Instant createdAt;
    private Instant updatedAt;
}