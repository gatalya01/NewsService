package com.example.NewsService.service;

import com.example.NewsService.dto.user.UserFilter;
import com.example.NewsService.model.User;
import com.example.NewsService.service.core.UniversalService;

public interface UserService extends UniversalService<User, UserFilter> {
}