package graphql.mapper;

import graphql.dto.todo.TodoCreateDto;
import graphql.dto.todo.TodoDto;
import graphql.entity.Todo;
import graphql.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TodoMapper {
    TodoDto toDto(Todo todo);

    default Todo toEntity(TodoCreateDto dto) {
        return Todo.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .level(dto.getLevel())
                .deadLine(dto.getDeadLine())
                .user(User.builder().id(dto.getUserId()).build())
                .build();
    }
}