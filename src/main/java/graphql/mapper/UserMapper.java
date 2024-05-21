package graphql.mapper;

import graphql.dto.todo.TodoDto;
import graphql.dto.user.GraphqlDto;
import graphql.dto.user.UserCreateDto;
import graphql.dto.user.UserDto;
import graphql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserCreateDto dto);

    UserDto toDto(User user);

    default GraphqlDto toGraphql(UserDto user, List<TodoDto> todos) {
        return GraphqlDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .todos(todos)
                .build();
    }
}