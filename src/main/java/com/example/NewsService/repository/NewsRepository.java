package com.example.NewsService.repository;


import com.example.NewsService.model.News;
import com.example.NewsService.service.core.AbstractUniversalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;



@Repository
public interface NewsRepository extends
        JpaRepository<News, Integer>,
        JpaSpecificationExecutor<News>,
        AbstractUniversalService.UniversalRepository<News> {
}