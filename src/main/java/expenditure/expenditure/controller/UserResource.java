package expenditure.expenditure.controller;


import expenditure.expenditure.entity.User;
import expenditure.expenditure.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    public User create(@RequestBody User user) {
        return userService.create(user);
    }
}
