package expenditure.expenditure.service;

import expenditure.expenditure.entity.User;
import expenditure.expenditure.repository.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        if (!checkPasswordLength(user.getPassword())) throw new RuntimeException("Length of password is lower than 4");
        if (existsUsername(user.getUsername())) throw new RuntimeException("This username is already exists");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public Boolean existsUsername(String username) {
        return repository.existsByUsername(username);
    }

    public User currentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        User currentUser = repository.findByUsername(username);
        if (currentUser == null) throw new RuntimeException("Problem with token, please re-sign in");
        return currentUser;
    }

    private Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }
}


