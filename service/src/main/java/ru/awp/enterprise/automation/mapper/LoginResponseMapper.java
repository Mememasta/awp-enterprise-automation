package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.response.LoginResponse;
import ru.awp.enterprise.automation.models.dto.UserDTO;

import java.util.function.BiFunction;

@Component
public class LoginResponseMapper implements BiFunction<UserDTO, String, LoginResponse> {

    @Override
    public LoginResponse apply(UserDTO userDTO, String token) {
        return LoginResponse.builder()
                .accessToken(token)
                .user(userDTO)
                .build();
    }
}