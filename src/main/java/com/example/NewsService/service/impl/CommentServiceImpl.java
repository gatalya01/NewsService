package com.example.NewsService.service.impl;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.comment.CommentFilter;
import com.example.NewsService.model.Comment;
import com.example.NewsService.repository.CommentRepository;
import com.example.NewsService.service.CommentService;
import com.example.NewsService.service.core.AbstractUniversalService;
import com.example.NewsService.specification.CommentSpecification;
import com.example.NewsService.util.ErrorMsg;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractUniversalService<Comment, CommentFilter> implements CommentService {
    public CommentServiceImpl(CommentRepository commentRepository) {
        super(commentRepository, ErrorMsg.COMMENT_BY_ID_NOT_FOUND);
    }

    @Loggable
    @Override
    public List<Comment> findAllByFilter(CommentFilter filter) {
        return super.repository.findAll(CommentSpecification.withFilter(filter));
    }
}
