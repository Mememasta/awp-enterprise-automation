package ru.awp.enterprise.automation.jwt;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter implements WebFilter {

    public static final String BEARER = "Bearer ";

    private final JwtUtils jwtUtils;
    private final ReactiveAuthenticationManager authenticationManager;

    @NonNull
    @Override
    public Mono<Void> filter(@NonNull ServerWebExchange exchange, @NonNull WebFilterChain chain) {
        var header = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        var jwt = Optional.ofNullable(header)
                .filter(it -> it.startsWith(BEARER))
                .map(it -> it.substring(7))
                .orElse(null);

        var claims = Optional.ofNullable(jwt)
                .map(it -> Mono.justOrEmpty(jwtUtils.getClaims(it)))
                .orElse(Mono.empty());

        var authentication = claims.filter(claim -> jwtUtils.isTokenValid(jwt, claim.getId()))
                .flatMap(claim -> authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(claim.getId(), claim.getSubject(), List.of(new SimpleGrantedAuthority("ROLE_USER")))));
        var context = authentication.map(ReactiveSecurityContextHolder::withAuthentication)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty());
        return context.flatMap(it -> it.map(chain.filter(exchange)::contextWrite)
                .orElse(chain.filter(exchange)));
    }
}
