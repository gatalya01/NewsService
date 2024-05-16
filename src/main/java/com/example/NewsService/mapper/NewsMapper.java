package com.example.NewsService.mapper;

import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.model.News;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsDTO toDTO(News news);
    News toEntity(NewsDTO newsDTO);
}