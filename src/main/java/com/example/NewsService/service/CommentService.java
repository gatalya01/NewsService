package com.example.NewsService.service;

import com.example.NewsService.dto.CommentDTO;
import com.example.NewsService.mapper.CommentMapper;
import com.example.NewsService.model.Comment;
import com.example.NewsService.model.News;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.CommentRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public List<CommentDTO> getAllComments() {
        return commentRepository.findAll().stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDTO getCommentById(int id) {
        return commentRepository.findById(id)
                .map(commentMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        News news = newsRepository.findById(commentDTO.getNewsId())
                .orElseThrow(() -> new EntityNotFoundException("News not found"));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setUser(user);
        comment.setNews(news);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public CommentDTO updateComment(int id, CommentDTO commentDTO) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }

        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        News news = newsRepository.findById(commentDTO.getNewsId())
                .orElseThrow(() -> new EntityNotFoundException("News not found"));

        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setId(id);
        comment.setUser(user);
        comment.setNews(news);
        return commentMapper.toDto(commentRepository.save(comment));
    }

    public void deleteComment(int id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }
}