package com.example.NewsService.mapper;


import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.news.*;
import com.example.NewsService.model.News;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(NewsMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CategoryMapper.class, CommentMapper.class})
public interface NewsMapper {
    News requestToNews(NewsUpsertRequest request);

    NewsResponse newsToNewsResponse(News news);

    @Loggable
    default NewsResponseForList newsToNewsResponseForList(News news) {
        return new NewsResponseForList(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getUser().getId(),
                news.getCategory().getId(),
                news.getComments().size()
        );
    }

    List<NewsResponseForList> newsListToListOfNewsResponse(List<News> news);

    @Loggable
    default NewsListResponse newsListToNewsListResponse(List<News> news) {
        return new NewsListResponse(this.newsListToListOfNewsResponse(news));
    }
}