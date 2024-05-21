package graphql.controller;

import graphql.dto.todo.TodoCreateDto;
import graphql.dto.todo.TodoDto;
import graphql.dto.user.GraphqlDto;
import graphql.dto.user.UserCreateDto;
import graphql.dto.user.UserDto;
import graphql.entity.Todo;
import graphql.entity.User;
import graphql.enums.Category;
import graphql.enums.Level;
import graphql.mapper.UserMapper;
import graphql.service.TodoService;
import graphql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GraphqlController {
    private final UserService userService;
    private final TodoService todoService;
    private final UserMapper userMapper;

    @SchemaMapping(typeName = "Query", value = "getUser")
    public User getUser(@Argument Integer id) {
        return userService.getUser(id);
    }

    @SchemaMapping(typeName = "Query", value = "getTodo")
    public Todo getTodo(@Argument Integer id) {
        return todoService.getTodo(id);
    }

    @QueryMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @QueryMapping
    public List<Todo> getAllTodo() {
        return todoService.getAllTodo();
    }

    @QueryMapping(value = "getAllTodosByLevel")
    public List<Todo> getAllByLevel(@Argument Level level) {
        return todoService.getAllByLevel(level);
    }

    @QueryMapping(value = "getAllTodosByCategory")
    public List<Todo> getAllByCategory(@Argument Category category) {
        return todoService.getAllByCategory(category);
    }

    @QueryMapping(value = "getAllTodosByDeadLine")
    public List<Todo> getAllByDeadLine(@Argument LocalDate deadLine) {
        return todoService.getAllByDeadLine(deadLine);
    }

    @QueryMapping(value = "getAllUsersWithTodos")
    public List<GraphqlDto> getAllByWithUsers() {
        List<UserDto> users = userService.getAll();
        List<GraphqlDto> graphqlDtos = new ArrayList<>();
        for (UserDto user : users) {
            List<TodoDto> todos = todoService.getAllByUserId(user.getId());
            graphqlDtos.add(userMapper.toGraphql(user, todos));
        }
        return graphqlDtos;
    }

    @MutationMapping
    public UserDto createUser(@Argument UserCreateDto dto) {
        return userService.create(dto);
    }

    @MutationMapping
    public Todo createTodo(@Argument TodoCreateDto dto) {
        return todoService.create(dto);
    }

    @MutationMapping
    public Todo completeTodo(@Argument Integer todoId) {
        return todoService.completeTodo(todoId);
    }

    @MutationMapping
    public String deleteTodo(@Argument Integer todoId) {
        todoService.deleteTodo(todoId);
        return "Todo Successfully deleted by ID: %s".formatted(todoId);
    }

    @MutationMapping
    public Todo updateTodo(@Argument TodoDto dto) {
        return todoService.updateTodo(dto);
    }

}
