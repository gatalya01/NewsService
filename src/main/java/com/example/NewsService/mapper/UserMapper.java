package com.example.NewsService.mapper;

import com.example.NewsService.dto.UserDTO;
import com.example.NewsService.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);

    @Mapping(target = "id", ignore = true)
    User toEntity(UserDTO userDTO);

    default User createUserFromDTO(UserDTO userDTO) {
        return new User(userDTO.getName());
    }
}