package com.example.NewsService.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentUpsertRequest {

    private String content;
    private int newsId;
    private int userId;
}