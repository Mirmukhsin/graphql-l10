package graphql.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link graphql.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto implements Serializable {
    private String username;
    private String email;
    private String password;
}