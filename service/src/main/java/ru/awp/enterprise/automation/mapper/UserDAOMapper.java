package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.request.SignUpRequest;

import java.util.List;
import java.util.function.BiFunction;

@Component
public class UserDAOMapper implements BiFunction<SignUpRequest, String, UserDAO> {

    @Override
    public UserDAO apply(SignUpRequest signUpRequest, String password) {
        return UserDAO.builder()
                .firstName(signUpRequest.firstName())
                .lastName(signUpRequest.lastName())
                .phoneNumber(signUpRequest.phoneNumber())
                .authorities(List.of()) //todo передавать роль пользователя из запроса
                .password(password)
                .build();

    }
}
