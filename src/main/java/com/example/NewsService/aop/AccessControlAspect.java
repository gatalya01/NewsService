package com.example.NewsService.aop;

import com.example.NewsService.model.Comment;
import com.example.NewsService.model.News;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.CommentRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AccessControlAspect {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Around("@annotation(com.example.NewsService.annotation.EditNewsAllowed)")
    public Object checkEditNewsPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получаем текущего пользователя
        User currentUser = userService.getCurrentUser();

        // Получаем id новости из параметров метода
        Object[] args = joinPoint.getArgs();
        Long newsId = (Long) args[0]; // Предполагается, что первый аргумент - id новости

        // Получаем новость по ее id
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("Новость с указанным id не найдена"));

        // Проверяем, является ли текущий пользователь автором новости
        if (!news.getAuthor().equals(currentUser)) {
            throw new IllegalAccessException("Вы не являетесь автором этой новости и не имеете прав на ее редактирование.");
        }

        // Если пользователь - автор новости, продолжаем выполнение метода
        return joinPoint.proceed();
    }

    @Around("@annotation(com.example.NewsService.annotation.DeleteCommentAllowed)")
    public Object checkDeleteCommentPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        // Получаем текущего пользователя
        User currentUser = userService.getCurrentUser();

        // Получаем id комментария из параметров метода
        Object[] args = joinPoint.getArgs();
        Long commentId = (Long) args[0]; // Предполагается, что первый аргумент - id комментария

        // Получаем комментарий по его id
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Комментарий с указанным id не найден"));

        // Проверяем, является ли текущий пользователь автором комментария
        if (!comment.getAuthor().equals(currentUser)) {
            throw new IllegalAccessException("Вы не являетесь автором этого комментария и не имеете прав на его удаление.");
        }

        // Если пользователь - автор комментария, продолжаем выполнение метода
        return joinPoint.proceed();
    }
}