package ru.awp.enterprise.automation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.server.WebFilter;
import ru.awp.enterprise.automation.config.properties.JwtProperties;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
@EnableReactiveMethodSecurity
@EnableConfigurationProperties(value = JwtProperties.class)
public class SecurityConfiguration {

    private final WebFilter jwtFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .cors(Customizer.withDefaults())
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable()
                .exceptionHandling()
                .and()
                .authorizeExchange(it -> it
                        .pathMatchers("/actuator/**").permitAll()
                        .pathMatchers("/metrics").permitAll()
                        .pathMatchers("/api/v1/authentication/auth").permitAll()
                        .pathMatchers("/api/v1/authentication/signup").hasRole("USER")
                        .pathMatchers("/**").hasRole("USER") //todo вынести в отдельный класс enum
                        .anyExchange().permitAll()
                )
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

}
