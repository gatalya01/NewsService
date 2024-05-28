package com.example.NewsService.service;

import com.example.NewsService.dto.UserDTO;
import com.example.NewsService.model.User;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(Long id);
    User getCurrentUser();
}
