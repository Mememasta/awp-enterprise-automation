package ru.awp.enterprise.automation.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import ru.awp.enterprise.automation.config.properties.CorsProperties;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(value = CorsProperties.class)
public class CorsConfig implements WebFluxConfigurer {

    private final CorsProperties properties;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(properties.getAllowedOrigins().toArray(new String[0]))
                .allowedMethods(properties.getAllowedMethods().toArray(new String[0]))
                .allowedHeaders(properties.getAllowedHeaders().toArray(new String[0]))
                .exposedHeaders(properties.getExpectedHeaders().toArray(new String[0]))
                .allowCredentials(properties.isAllowCredentials())
                .maxAge(properties.getMaxAge());
    }
}
