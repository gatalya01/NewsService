package com.example.NewsService.mapper;

import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface NewsMapper {

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "author.id", target = "authorId")
    @Mapping(target = "commentCount", expression = "java(news.getComments() != null ? news.getComments().size() : 0)")
    NewsDTO toDTO(News news);

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "authorId", target = "author.id")
    News toEntity(NewsDTO newsDTO);
}