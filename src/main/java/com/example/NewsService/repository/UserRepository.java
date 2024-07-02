package com.example.NewsService.repository;

import com.example.NewsService.model.User;
import com.example.NewsService.service.core.AbstractUniversalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends
        JpaRepository<User, Integer>,
        JpaSpecificationExecutor<User>,
        AbstractUniversalService.UniversalRepository<User> {
}