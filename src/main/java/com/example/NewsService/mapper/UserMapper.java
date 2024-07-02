package com.example.NewsService.mapper;

import com.example.NewsService.aop.Loggable;
import com.example.NewsService.dto.user.*;
import com.example.NewsService.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {CommentMapper.class, NewsMapper.class})
public interface UserMapper {

    User requestToUser(UserUpsertRequest request);

    UserResponse userToUserResponse(User user);

    @Loggable
    default UserResponseForList userToUserResponseForList(User user) {
        return new UserResponseForList(
                user.getId(),
                user.getName(),
                user.getNews().size(),
                user.getComments().size()
        );
    }

    List<UserResponseForList> userListToListOfUserResponseForList(List<User> users);

    @Loggable
    default UserListResponse userListToUserListResponse(List<User> users) {
        return new UserListResponse(this.userListToListOfUserResponseForList(users));
    }
}