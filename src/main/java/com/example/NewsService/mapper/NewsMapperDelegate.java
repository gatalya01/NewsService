package com.example.NewsService.mapper;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.news.NewsUpsertRequest;
import com.example.NewsService.model.News;
import com.example.NewsService.service.CategoryService;
import com.example.NewsService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public abstract class NewsMapperDelegate implements NewsMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Loggable
    @Override
    public News requestToNews(NewsUpsertRequest request) {
        News news = new News(request.getTitle(), request.getContent());

        news.setUser(this.userService.findById(request.getUserId()));
        news.setCategory(this.categoryService.findById(request.getCategoryId()));

        return news;
    }
}