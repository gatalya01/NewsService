package com.example.NewsService.dto.user;

import com.example.NewsService.dto.comment.CommentResponse;
import com.example.NewsService.dto.news.NewsResponseForList;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class UserResponse {
    private int id;
    private String name;
    private List<NewsResponseForList> news = new ArrayList<>();
    private List<CommentResponse> comments = new ArrayList<>();

    public UserResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
