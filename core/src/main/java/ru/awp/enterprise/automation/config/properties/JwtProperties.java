package ru.awp.enterprise.automation.config.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("jwt")
public class JwtProperties {

    /**
     * Секретный ключ для генерации jwt
     */
    @NotBlank
    private String secretKey;

    /**
     * Кол-во дней до сгорания токена
     */
    private long expirationDate;

}
