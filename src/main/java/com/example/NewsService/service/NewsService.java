package com.example.NewsService.service;

import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.mapper.NewsMapper;
import com.example.NewsService.model.Category;
import com.example.NewsService.model.News;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.CategoryRepository;
import com.example.NewsService.repository.NewsRepository;
import com.example.NewsService.repository.UserRepository;
import com.example.NewsService.specifications.NewsSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    public NewsDTO getNewsById(int id) {
        return newsRepository.findById(id)
                .map(newsMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("News not found"));
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        User user = userRepository.findById(newsDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Category category = categoryRepository.findById(newsDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        News news = newsMapper.toEntity(newsDTO);
        news.setUser(user);
        news.setCategory(category);
        return newsMapper.toDto(newsRepository.save(news));
    }

    public NewsDTO updateNews(int id, NewsDTO newsDTO) {
        if (!newsRepository.existsById(id)) {
            throw new EntityNotFoundException("News not found");
        }

        User user = userRepository.findById(newsDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Category category = categoryRepository.findById(newsDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        News news = newsMapper.toEntity(newsDTO);
        news.setId(id);
        news.setUser(user);
        news.setCategory(category);
        return newsMapper.toDto(newsRepository.save(news));
    }

    public void deleteNews(int id) {
        if (!newsRepository.existsById(id)) {
            throw new EntityNotFoundException("News not found");
        }
        newsRepository.deleteById(id);
    }
}