package com.example.NewsService.aop;

import com.example.NewsService.model.Comment;
import com.example.NewsService.model.News;

import com.example.NewsService.repository.CommentRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessControlAspect {
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    @Before("execution(* com.example.news.service.NewsService.updateNews(..)) && args(id, ..)")
    public void checkUpdateNewsAccess(JoinPoint joinPoint, int id) {
        Object[] args = joinPoint.getArgs();
        int userId = (int) args[1]; // Предполагаем, что userId передается вторым аргументом

        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));

        if (news.getUser().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to update this news");
        }
    }

    @Before("execution(* com.example.news.service.NewsService.deleteNews(..)) && args(id)")
    public void checkDeleteNewsAccess(JoinPoint joinPoint, int id) {
        Object[] args = joinPoint.getArgs();
        int userId = (int) args[1]; // Предполагаем, что userId передается вторым аргументом

        News news = newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));

        if (news.getUser().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to delete this news");
        }
    }

    @Before("execution(* com.example.news.service.CommentService.updateComment(..)) && args(id, ..)")
    public void checkUpdateCommentAccess(JoinPoint joinPoint, int id) {
        Object[] args = joinPoint.getArgs();
        int userId = (int) args[1]; // Предполагаем, что userId передается вторым аргументом

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (comment.getUser().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to update this comment");
        }
    }

    @Before("execution(* com.example.news.service.CommentService.deleteComment(..)) && args(id)")
    public void checkDeleteCommentAccess(JoinPoint joinPoint, int id) {
        Object[] args = joinPoint.getArgs();
        int userId = (int) args[1]; // Предполагаем, что userId передается вторым аргументом

        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));

        if (comment.getUser().getId() != userId) {
            throw new AccessDeniedException("You do not have permission to delete this comment");
        }
    }
}