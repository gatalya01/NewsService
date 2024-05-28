package com.example.NewsService.service;

import com.example.NewsService.dto.CommentDTO;
import com.example.NewsService.mapper.CommentMapper;
import com.example.NewsService.model.Comment;
import com.example.NewsService.model.News;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.CommentRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper,
                          NewsRepository newsRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        News news = newsRepository.findById(commentDTO.getNewsId())
                .orElseThrow(() -> new IllegalArgumentException("Новость с указанным id не найдена"));
        User author = userRepository.findById(commentDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с указанным id не найден"));

        comment.setNews(news);
        comment.setAuthor(author);

        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    public List<CommentDTO> getAllCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findByNewsId(newsId);
        return comments.stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CommentDTO> getCommentById(Long id) {
        return commentRepository.findById(id).map(commentMapper::toDTO);
    }

    public void updateComment(CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Комментарий с указанным id не найден"));

        existingComment.setContent(commentDTO.getContent());

        News news = newsRepository.findById(commentDTO.getNewsId())
                .orElseThrow(() -> new IllegalArgumentException("Новость с указанным id не найдена"));
        User author = userRepository.findById(commentDTO.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь с указанным id не найден"));

        existingComment.setNews(news);
        existingComment.setAuthor(author);

        commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Комментарий с указанным id не найден"));

        commentRepository.delete(existingComment);
    }
}