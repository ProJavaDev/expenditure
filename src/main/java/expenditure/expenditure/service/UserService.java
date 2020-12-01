package expenditure.expenditure.service;

import expenditure.expenditure.dto.UserDto;

public interface UserService {

    UserDto create(UserDto dto);

    Boolean existsUsername(String username);

    Boolean checkPasswordLength(String password);
}
