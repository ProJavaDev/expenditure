package expenditure.expenditure.controller;


import expenditure.expenditure.dto.UserDto;
import expenditure.expenditure.entity.User;
import expenditure.expenditure.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @PostMapping("register")
    public UserDto create(@RequestBody UserDto dto) {
        return userServiceImpl.create(dto);
    }
}
