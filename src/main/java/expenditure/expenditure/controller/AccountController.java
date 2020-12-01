package expenditure.expenditure.controller;

import expenditure.expenditure.entity.User;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    private final UserServiceImpl service;
    private final UserRepository repository;

    public AccountController(UserServiceImpl service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping("account")
    public ResponseEntity<?> getAccount(){
        String login=service.currentUser().getUsername();
        User user=repository.findByUsername(login);
        return ResponseEntity.ok(user);
    }

}
