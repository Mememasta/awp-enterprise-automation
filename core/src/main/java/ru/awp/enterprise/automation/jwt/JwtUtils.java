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
     * @param id идентификатор клиента
     * @return jwt-токен
     */
    public String issueToken(String id) {
        return issueToken(id, null, Map.of());
    }

    /**
     * Сгенерировать токен по идентификатору работника и паролю
     *
     * @param id идентификатор клиента
     * @param subject пароль клиента
     * @return jwt-токен
     */
    public String issueToken(String id, String subject) {
        return issueToken(id, subject, Map.of());
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param id идентификатор работника
     * @param scopes перечесление ролей
     * @return jwt-токен
     */
    public String issueToken(String id, String ...scopes) {
        return issueToken(id, null, Map.of("scopes", scopes));
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param id идентификатор работника
     * @param scopes список ролей
     * @return jwt-токен
     */
    public String issueToken(String id, List<String> scopes) {
        return issueToken(id, null, Map.of("scopes", scopes));
    }

    /**
     * Сгенерировать токен по идентификатору работника с ролью
     *
     * @param subject идентификатор работника
     * @param claims список ролей
     * @return jwt-токен
     */
    public String issueToken(
            String id,
            String subject,
            Map<String, Object> claims) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setId(id)
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
    public String getId(String token) {
        return getClaims(token).getId();
    }

    /**
     * Получить пароль работника по jwt-токену
     *
     * @param token jwt-токен
     * @return пароль работника
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
     * @param phoneNumber идентификатор работника
     */
    public boolean isTokenValid(String jwt, String phoneNumber) {
        var id = getId(jwt);
        return Objects.equals(id, phoneNumber) && !isTokenExpired(jwt);
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