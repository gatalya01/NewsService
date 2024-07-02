package com.example.NewsService.mapper;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.comment.CommentUpsertRequest;
import com.example.NewsService.model.Comment;
import com.example.NewsService.service.NewsService;
import com.example.NewsService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CommentMapperDelegate implements CommentMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private NewsService newsService;

    @Loggable
    @Override
    public Comment requestToComment(CommentUpsertRequest request) {
        final Comment comment = new Comment(request.getContent());

        comment.setNews(this.newsService.findById(request.getNewsId()));
        comment.setUser(this.userService.findById(request.getUserId()));

        return comment;
    }
}