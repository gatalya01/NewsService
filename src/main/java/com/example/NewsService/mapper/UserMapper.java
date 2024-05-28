package com.example.NewsService.mapper;

import com.example.NewsService.dto.UserDTO;
import com.example.NewsService.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

    void updateEntityFromDTO(UserDTO userDTO, @MappingTarget User user);
}