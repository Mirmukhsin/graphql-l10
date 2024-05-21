package graphql.service;

import graphql.dto.user.UserCreateDto;
import graphql.dto.user.UserDto;
import graphql.entity.User;
import lombok.NonNull;

import java.util.List;

public interface UserService {
    UserDto create(@NonNull UserCreateDto dto);

    List<UserDto> getAll();

    User getUser(@NonNull Integer id);

    List<User> getAllUser();
}
