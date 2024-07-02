package com.example.NewsService.service.impl;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.news.NewsFilter;
import com.example.NewsService.model.News;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.service.NewsService;
import com.example.NewsService.service.core.AbstractUniversalService;
import com.example.NewsService.specification.NewsSpecification;
import com.example.NewsService.util.ErrorMsg;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends AbstractUniversalService<News, NewsFilter> implements NewsService {
    public NewsServiceImpl(NewsRepository newsRepository) {
        super(newsRepository, ErrorMsg.NEWS_BY_ID_NOT_FOUND);
    }

    @Loggable
    @Override
    public List<News> findAllByFilter(NewsFilter filter) {
        return super.repository.findAll(NewsSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }
}
