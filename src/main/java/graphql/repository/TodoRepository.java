package graphql.repository;

import graphql.entity.Todo;
import graphql.enums.Category;
import graphql.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByUserId(Integer id);

    List<Todo> findAllByLevel(Level level);

    List<Todo> findAllByCategory(Category category);

    List<Todo> findAllByDeadLine(LocalDate deadLine);
}