package graphql.dto.todo;

import graphql.enums.Category;
import graphql.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link graphql.entity.Todo}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private Category category;
    private Level level;
    private LocalDate deadLine;
    private boolean completed;
}