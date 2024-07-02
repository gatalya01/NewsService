package com.example.NewsService.service;

import com.example.NewsService.dto.comment.CommentFilter;
import com.example.NewsService.model.Comment;
import com.example.NewsService.service.core.UniversalService;

public interface CommentService extends UniversalService<Comment, CommentFilter> {
}