package graphql.service;

import graphql.dto.todo.TodoCreateDto;
import graphql.dto.todo.TodoDto;
import graphql.entity.Todo;
import graphql.enums.Category;
import graphql.enums.Level;
import graphql.mapper.TodoMapper;
import graphql.repository.TodoRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;
    private final TodoMapper mapper;

    @Override
    public List<TodoDto> getAllByUserId(@NonNull Integer id) {
        List<Todo> todos = repository.findAllByUserId(id);
        List<TodoDto> todoDtos = new ArrayList<>();
        for (Todo todo : todos) {
            todoDtos.add(mapper.toDto(todo));
        }
        return todoDtos;
    }

    @Override
    public List<Todo> getAllByLevel(@NonNull Level level) {
        return repository.findAllByLevel(level);
    }

    @Override
    public List<Todo> getAllByCategory(@NonNull Category category) {
        return repository.findAllByCategory(category);
    }

    @Override
    public List<Todo> getAllByDeadLine(@NonNull LocalDate deadLine) {
        return repository.findAllByDeadLine(deadLine);
    }

    @Override
    public List<Todo> getAllTodo() {
        return repository.findAll();
    }

    @Override
    public Todo getTodo(@NonNull Integer id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    @Override
    public Todo create(@NonNull TodoCreateDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

    @Override
    public Todo completeTodo(@NonNull Integer todoId) {
        Todo todo = getTodo(todoId);
        todo.setCompleted(!todo.isCompleted());
        repository.save(todo);
        return todo;
    }

    @Override
    public void deleteTodo(@NonNull Integer todoId) {
        repository.deleteById(todoId);
    }

    @Override
    public Todo updateTodo(@NonNull TodoDto dto) {
        Todo todo = getTodo(dto.getId());
        todo.setTitle(Objects.requireNonNullElse(dto.getTitle(), todo.getTitle()));
        todo.setDescription(Objects.requireNonNullElse(dto.getDescription(), todo.getDescription()));
        todo.setCategory(Objects.requireNonNullElse(dto.getCategory(), todo.getCategory()));
        todo.setLevel(Objects.requireNonNullElse(dto.getLevel(), todo.getLevel()));
        todo.setDeadLine(Objects.requireNonNullElse(dto.getDeadLine(), todo.getDeadLine()));
        todo.setCompleted(dto.isCompleted());
        return repository.save(todo);
    }
}
