package com.example.NewsService.service;

import com.example.NewsService.dto.CommentDTO;
import com.example.NewsService.mapper.CommentMapper;
import com.example.NewsService.model.Comment;
import com.example.NewsService.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public CommentDTO createComment(CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDTO(savedComment);
    }

    public List<CommentDTO> getCommentsByNewsId(Long newsId) {
        List<Comment> comments = commentRepository.findByNewsId(newsId);
        return commentMapper.toDTOs(comments);
    }

    public Optional<CommentDTO> getCommentById(Long id) {
        return commentRepository.findById(id).map(commentMapper::toDTO);
    }

    public void updateComment(CommentDTO newCommentDTO) {
        Comment existingComment = commentRepository.findById(newCommentDTO.getId()).orElse(null);
        if (existingComment == null) {
            throw new IllegalArgumentException("Комментарий с указанным id не найден");
        }
        existingComment.setContent(newCommentDTO.getContent());
        commentRepository.save(existingComment);
    }

    public void deleteComment(Long commentId) {
        Comment existingComment = commentRepository.findById(commentId).orElse(null);
        if (existingComment == null) {
            throw new IllegalArgumentException("Комментарий с указанным id не найден");
        }
        commentRepository.deleteById(commentId);
    }
}