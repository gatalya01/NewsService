package com.example.NewsService.mapper;

import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.model.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsDTO toDto(News news);

    @Mapping(target = "id", ignore = true)
    News toEntity(NewsDTO newsDTO);

    default News createNewsFromDTO(NewsDTO newsDTO) {
        return new News(newsDTO.getTitle(), newsDTO.getContent());
    }
}