package ru.awp.enterprise.automation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.WebFilter;
import ru.awp.enterprise.automation.jwt.JwtFilter;
import ru.awp.enterprise.automation.jwt.JwtUtils;
import ru.awp.enterprise.automation.service.UserService;

@Configuration
@RequiredArgsConstructor
public class CustomJwtFilter {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        return new CustomAuthenticationManager(userService::findByPhone, passwordEncoder);
    }

    @Bean
    public WebFilter jwtFilter(ReactiveAuthenticationManager authenticationManager) {
        return new JwtFilter(jwtUtils, authenticationManager);
    }


}
