package graphql.dto.user;

import graphql.dto.todo.TodoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link graphql.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GraphqlDto implements Serializable {
    private Integer id;
    private String username;
    private String email;
    private List<TodoDto> todos;
}