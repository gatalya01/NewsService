package com.example.NewsService.service;

import com.example.NewsService.dto.news.NewsFilter;
import com.example.NewsService.model.News;
import com.example.NewsService.service.core.UniversalService;

public interface NewsService extends UniversalService<News, NewsFilter> {
}