package graphql.service;

import graphql.dto.user.UserCreateDto;
import graphql.dto.user.UserDto;
import graphql.entity.User;
import graphql.mapper.UserMapper;
import graphql.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    @Override
    public User getUser(@NonNull Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public UserDto create(@NonNull UserCreateDto dto) {
        User savedUser = repository.save(mapper.toEntity(dto));
        return mapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getAll() {

        List<User> users = repository.findAll();
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : users) {
            dtoList.add(mapper.toDto(user));
        }
        return dtoList;
    }

    @Override
    public List<User> getAllUser() {
        return repository.findAll();
    }
}
