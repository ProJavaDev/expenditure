package expenditure.expenditure.service.impl;

import expenditure.expenditure.dto.UserDto;
import expenditure.expenditure.entity.User;
import expenditure.expenditure.exception.UserNotFoundException;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.service.UserService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto create(UserDto dto) {
        if (!checkPasswordLength(dto.getPassword()))
            throw new UserNotFoundException("Length of password is lower than 4");
        if (existsUsername(dto.getUsername())) throw new UserNotFoundException("This username is already exists");
//        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        return UserDto.toDto(repository.save(
                new User(
                        dto.getUsername(),
                        passwordEncoder.encode(dto.getPassword()),
                        dto.getRoles()
                )
        ));
    }

    public Boolean existsUsername(String username) {
        return repository.existsByUsername(username);
    }

    public User currentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = securityContext.getAuthentication().getName();
        User currentUser = repository.findByUsername(username);
        if (currentUser == null) throw new UserNotFoundException("Problem with token, please re-sign in");
        return currentUser;
    }

    public Boolean checkPasswordLength(String password) {
        return password.length() >= 4;
    }
}


