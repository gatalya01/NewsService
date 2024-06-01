package com.example.NewsService.mapper;

import com.example.NewsService.dto.CommentDTO;
import com.example.NewsService.model.Comment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDTO toDto(Comment comment);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "news.id", source = "newsId")
    @Mapping(target = "user.id", source = "userId")
    Comment toEntity(CommentDTO commentDTO);}