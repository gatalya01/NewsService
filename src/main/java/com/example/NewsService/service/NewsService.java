package com.example.NewsService.service;

import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.mapper.NewsMapper;
import com.example.NewsService.model.Category;
import com.example.NewsService.model.News;
import com.example.NewsService.repository.CategoryRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.specifications.NewsSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final CategoryRepository categoryRepository;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, CategoryRepository categoryRepository) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.categoryRepository = categoryRepository;
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = newsMapper.toEntity(newsDTO);
        News savedNews = newsRepository.save(news);
        return newsMapper.toDTO(savedNews);
    }

    public Page<NewsDTO> getAllNews(Pageable pageable) {
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.map(newsMapper::toDTO);
    }

    public Optional<NewsDTO> getNewsById(Long id) {
        return newsRepository.findById(id).map(newsMapper::toDTO);
    }

    public void updateNews(NewsDTO updatedNewsDTO) {
        News existingNews = newsRepository.findById(updatedNewsDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Новость с указанным id не найдена"));

        existingNews.setTitle(updatedNewsDTO.getTitle());
        existingNews.setContent(updatedNewsDTO.getContent());

        Category category = categoryRepository.findById(updatedNewsDTO.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Категория с указанным id не найдена"));
        existingNews.setCategory(category);

        newsRepository.save(existingNews);
    }

    public void deleteNews(Long newsId) {
        News existingNews = newsRepository.findById(newsId)
                .orElseThrow(() -> new IllegalArgumentException("Новость с указанным id не найдена"));

        newsRepository.delete(existingNews);
    }

    public Page<NewsDTO> filterNews(Long categoryId, Long authorId, Pageable pageable) {
        Specification<News> spec = Specification.where(null);
        if (categoryId != null) {
            spec = spec.and(NewsSpecifications.hasCategory(categoryId));
        }
        if (authorId != null) {
            spec = spec.and(NewsSpecifications.hasAuthor(authorId));
        }
        return newsRepository.findAll(spec, pageable).map(newsMapper::toDTO);
    }
}