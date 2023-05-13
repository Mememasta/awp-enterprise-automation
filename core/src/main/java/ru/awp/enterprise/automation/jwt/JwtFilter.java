package ru.awp.enterprise.automation.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dto.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    public static final String BEARER = "Bearer ";

    private final JwtUtils jwtUtils;
    private final Function<String, Mono<UserDTO>> userService;

    @NonNull
    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        var header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        var jwt = Optional.ofNullable(header)
                .filter(it -> it.startsWith(BEARER))
                .map(it -> it.substring(7))
                .orElse(null);

        var user = Optional.ofNullable(jwt)
//                .filter(user -> ReactiveSecurityContextHolder.getContext().map(SecurityContext::getAuthentication) == null)
                .map(jwtUtils::getSubject)
                .map(userService)
                .orElse(Mono.empty());

        return user.filter(it -> jwtUtils.isTokenValid(jwt, it.id()))
                .map(it -> new UsernamePasswordAuthenticationToken(it, jwt, List.of(new SimpleGrantedAuthority("ROLE_USER"))))
                .flatMap(auth -> chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth)))
                .switchIfEmpty(chain.filter(exchange));
    }
}
