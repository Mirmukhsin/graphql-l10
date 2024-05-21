package graphql.service;

import graphql.dto.todo.TodoCreateDto;
import graphql.dto.todo.TodoDto;
import graphql.entity.Todo;
import graphql.enums.Category;
import graphql.enums.Level;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;

public interface TodoService {
    List<Todo> getAllByLevel(@NonNull Level level);

    List<Todo> getAllByCategory(@NonNull Category category);

    List<Todo> getAllByDeadLine(@NonNull LocalDate deadLine);

    List<TodoDto> getAllByUserId(@NonNull Integer id);


    List<Todo> getAllTodo();

    Todo getTodo(@NonNull Integer id);

    Todo create(@NonNull TodoCreateDto dto);

    Todo completeTodo(@NonNull Integer todoId);

    void deleteTodo(@NonNull Integer todoId);

    Todo updateTodo(@NonNull TodoDto dto);
}
