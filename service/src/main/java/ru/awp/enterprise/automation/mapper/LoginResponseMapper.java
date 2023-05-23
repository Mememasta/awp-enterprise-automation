package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.response.LoginResponse;

import java.util.function.Function;

@Component
public class LoginResponseMapper implements Function<String, LoginResponse> {

    @Override
    public LoginResponse apply(String token) {
        return LoginResponse.builder()
                .accessToken(token)
                .build();
    }
}