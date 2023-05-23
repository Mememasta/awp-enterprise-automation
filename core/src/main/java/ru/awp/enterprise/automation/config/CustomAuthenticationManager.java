package ru.awp.enterprise.automation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.UserDAO;

import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {

    private final Function<String, Mono<UserDAO>> userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        var password = Optional.ofNullable(authentication.getCredentials())
                .map(String::valueOf)
                .orElse("");
        var userDAO = Mono.just(authentication)
                .flatMap(auth -> userService.apply(auth.getPrincipal().toString()))
                .filter(user -> passwordEncoder.matches(password, user.password()));
        return userDAO.map(it -> new UsernamePasswordAuthenticationToken(it.phoneNumber(), password, authentication.getAuthorities()));
    }
}
