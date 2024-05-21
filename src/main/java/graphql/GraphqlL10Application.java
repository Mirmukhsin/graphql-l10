package graphql;

import graphql.entity.Todo;
import graphql.entity.User;
import graphql.enums.Category;
import graphql.enums.Level;
import graphql.repository.TodoRepository;
import graphql.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class GraphqlL10Application {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlL10Application.class, args);
    }

    @Bean
    public ApplicationRunner runner(UserRepository userRepository, TodoRepository todoRepository) {
        return args -> {
            User panda = new User(1, "Panda", "panda@gmail.com", "9779");
            User simple = new User(2, "Simple", "simple@gmail.com", "123");
            userRepository.saveAll(List.of(panda, simple));

            todoRepository.saveAll(List.of(
                    new Todo(1, "Read Book", "New Book", Category.STUDY, Level.LOW, LocalDate.now(), false, panda),
                    new Todo(2, "Do Todos", "New Todos", Category.WORK, Level.MEDIUM, LocalDate.now(), false, panda),
                    new Todo(3, "Listen Musics", "New Musics", Category.STUDY, Level.LOW, LocalDate.now(), false, simple),
                    new Todo(4, "Watch Films", "New Films", Category.WORK, Level.MEDIUM, LocalDate.now(), false, simple)
            ));
        };
    }

}
