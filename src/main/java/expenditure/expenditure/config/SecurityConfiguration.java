package expenditure.expenditure.config;

import expenditure.expenditure.entity.Role;
import expenditure.expenditure.repository.RoleRepository;
import expenditure.expenditure.security.JwtConfigurer;
import expenditure.expenditure.security.JwtTokenProvider;
import expenditure.expenditure.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;

    public SecurityConfiguration(CustomUserDetailService userDetailsService, JwtTokenProvider jwtTokenProvider, RoleRepository roleRepository) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;

        this.roleRepository = roleRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //        auth
    //                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    //    }

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
                .antMatchers("/api/v1/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }


    @PostConstruct
    public void create() {
        if (roleRepository.count() == 0) {
            Role role = new Role();
            roleRepository.deleteAll();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
            role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
    }
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
