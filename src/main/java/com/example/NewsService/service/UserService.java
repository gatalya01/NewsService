package com.example.NewsService.service;

import com.example.NewsService.dto.UserDTO;
import com.example.NewsService.mapper.UserMapper;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким именем уже существует");
        }
        User user = userMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOs(users);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    public void updateUser(UserDTO newUserDTO) {
        User existingUser = userRepository.findById(newUserDTO.getId()).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("Пользователь с указанным id не найден");
        }
        existingUser.setUsername(newUserDTO.getUsername());
        existingUser.setPassword(newUserDTO.getPassword());
        userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser == null) {
            throw new IllegalArgumentException("Пользователь с указанным id не найден");
        }
        userRepository.deleteById(userId);
    }
}