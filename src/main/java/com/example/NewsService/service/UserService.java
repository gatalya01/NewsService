package com.example.NewsService.service;

import com.example.NewsService.dto.UserDTO;
import com.example.NewsService.mapper.UserMapper;
import com.example.NewsService.model.User;
import com.example.NewsService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(int id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDTO updateUser(int id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        User user = userMapper.toEntity(userDTO);
        user.setId(id);
        return userMapper.toDto(userRepository.save(user));
    }

    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }
}