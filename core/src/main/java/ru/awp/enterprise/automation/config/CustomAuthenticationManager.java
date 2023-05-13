package ru.awp.enterprise.automation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.jwt.JwtUtils;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtils jwtUtils;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> jwtUtils.getClaims(auth.getCredentials().toString()))
                .onErrorResume(throwable -> Mono.error(new RuntimeException()))
                .map(claims -> new UsernamePasswordAuthenticationToken(claims.getSubject(), authentication.getCredentials(), Collections.singleton(new SimpleGrantedAuthority("USER"))));
    }
}
