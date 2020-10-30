package expenditure.expenditure.config;

import expenditure.expenditure.entity.Role;
import expenditure.expenditure.repository.RoleRepository;
import expenditure.expenditure.repository.UserRepository;
import expenditure.expenditure.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final RoleRepository roleRepository;

    public SecurityConfiguration(CustomUserDetailService userDetailsService, UserRepository userRepository, RoleRepository roleRepository) {
        this.userDetailsService = userDetailsService;

        this.roleRepository = roleRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/spend/get-all").hasRole("ADMIN")
                .antMatchers("/api/v1/spend/edit-all").hasRole("ADMIN")
                .antMatchers("/api/v1/spend").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    @PostConstruct
    public void create() {
        Role role = new Role();
        roleRepository.deleteAll();
//        role.setId(1L);
        role.setName("ROLE_ADMIN");
        roleRepository.save(role);
        role = new Role();
        role.setName("ROLE_USER");
//        role.setId(2L);
        roleRepository.save(role);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
