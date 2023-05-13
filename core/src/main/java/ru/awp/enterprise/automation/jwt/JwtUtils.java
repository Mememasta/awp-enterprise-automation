package ru.awp.enterprise.automation.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.awp.enterprise.automation.config.properties.JwtProperties;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class JwtUtils {

    private final JwtProperties properties;

    /**
     * Сгенерировать токен по идентификатору работника
     *
     * @param subject идентификатор клиента
     * @return jwt-токен
     */
    public String issueToken(String subject) {
        return issueToken(subject, Map.of());
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param subject идентификатор работника
     * @param scopes перечесление ролей
     * @return jwt-токен
     */
    public String issueToken(String subject, String ...scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param subject идентификатор работника
     * @param scopes список ролей
     * @return jwt-токен
     */
    public String issueToken(String subject, List<String> scopes) {
        return issueToken(subject, Map.of("scopes", scopes));
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param subject идентификатор работника
     * @param claims список ролей
     * @return jwt-токен
     */
    public String issueToken(
            String subject,
            Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(
                        Date.from(
                                Instant.now().plus(properties.getExpirationDate(), DAYS)
                        )
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Получить идентификатор работника по jwt-токену
     *
     * @param token jwt-токен
     * @return идентификатор работника
     */
    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * Получить json-тело токена
     *
     * @param token jwt-токен
     * @return {@link Claims} данные вшитие в токен
     */
    public Claims getClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(properties.getSecretKey().getBytes());
    }

    /**
     * Является ли токен валидным
     *
     * @param jwt токен
     * @param id идентификатор работника
     */
    public boolean isTokenValid(String jwt, String id) {
        var subject = getSubject(jwt);
        return Objects.equals(subject, id) && !isTokenExpired(jwt);
    }

    /**
     * Является ли токен просроченым
     *
     * @param jwt токен
     */
    private boolean isTokenExpired(String jwt) {
        var today = Date.from(Instant.now());
        return getClaims(jwt).getExpiration().before(today);
    }

}