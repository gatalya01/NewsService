package com.example.NewsService.dto;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private int id;
    private String name;
    private Instant createdAt;
    private Instant updatedAt;
}