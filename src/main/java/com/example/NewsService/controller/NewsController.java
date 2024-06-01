package com.example.NewsService.controller;


import com.example.NewsService.dto.NewsDTO;
import com.example.NewsService.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public NewsDTO getNewsById(@PathVariable int id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.createNews(newsDTO);
    }

    @PutMapping("/{id}")
    public NewsDTO updateNews(@PathVariable int id, @RequestBody NewsDTO newsDTO) {
        return newsService.updateNews(id, newsDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable int id) {
        newsService.deleteNews(id);
    }
}