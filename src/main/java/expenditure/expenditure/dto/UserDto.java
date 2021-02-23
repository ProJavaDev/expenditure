package expenditure.expenditure.dto;

import expenditure.expenditure.entity.Role;
import expenditure.expenditure.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String  password;
    private Set<Role> roles;

    public static UserDto toDto(User user){
        UserDto userDto=new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
        );
        return userDto;
    }
}

