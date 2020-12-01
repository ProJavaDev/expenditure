package expenditure.expenditure.controller;


import java.util.HashMap;
import java.util.Map;


import expenditure.expenditure.controller.vm.LoginVM;
import expenditure.expenditure.entity.User;
import expenditure.expenditure.exception.UserNotFoundException;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.security.JwtTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/v1"})
public class UserJwtController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public UserJwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping({"/login"})
    public ResponseEntity login(@RequestBody LoginVM loginVM) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword()));
        User user = this.userRepository.findByUsername(loginVM.getUsername());
        if (user == null) {
            throw new UserNotFoundException("This user does not exist");
        } else {
            String token = this.jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            Map<Object, Object> map = new HashMap();
            map.put("username", user.getUsername());
            map.put("token", token);
            return ResponseEntity.ok(map);
        }
    }
}

