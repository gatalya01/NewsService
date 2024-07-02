package com.example.NewsService.repository;

import com.example.NewsService.model.Comment;
import com.example.NewsService.service.core.AbstractUniversalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends
        JpaRepository<Comment, Integer>,
        JpaSpecificationExecutor<Comment>,
        AbstractUniversalService.UniversalRepository<Comment> {
}