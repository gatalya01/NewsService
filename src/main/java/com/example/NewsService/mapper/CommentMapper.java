package com.example.NewsService.mapper;

import com.example.NewsService.dto.CommentDTO;
import com.example.NewsService.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "news.id", target = "newsId")
    @Mapping(source = "author.id", target = "authorId")
    CommentDTO toDTO(Comment comment);
    @Mapping(source = "newsId", target = "news.id")
    @Mapping(source = "authorId", target = "author.id")
    Comment toEntity(CommentDTO commentDTO);

    List<CommentDTO> toDTOs(List<Comment> comments);
}