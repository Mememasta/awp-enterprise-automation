package ru.awp.enterprise.automation.config.properties;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@ConfigurationProperties("cors")
public class CorsProperties {

    @NotEmpty
    private List<String> allowedOrigins;

    @NotEmpty
    private List<String> allowedMethods;

    @NotEmpty
    private List<String> allowedHeaders;

    @NotEmpty
    private List<String> expectedHeaders;

    private long maxAge;

    private boolean allowCredentials;

}
