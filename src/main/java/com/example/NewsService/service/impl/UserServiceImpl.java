package com.example.NewsService.service.impl;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.user.UserFilter;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.UserRepository;
import com.example.NewsService.service.UserService;
import com.example.NewsService.service.core.AbstractUniversalService;
import com.example.NewsService.util.ErrorMsg;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractUniversalService<User, UserFilter> implements UserService {
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository, ErrorMsg.USER_BY_ID_NOT_FOUND);
    }

    @Loggable
    @Override
    public List<User> findAllByFilter(UserFilter filter) {
        return super.repository.findAll(
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())
        ).getContent();
    }
}