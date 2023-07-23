package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;

import java.util.List;
import java.util.UUID;
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

    public UserDAO apply(UUID uuid, UserChangeRequest request, UserDAO userDAO) {
        return UserDAO.builder()
                .id(uuid)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phoneNumber(request.phoneNumber())
                .authorities(request.authorities())
                .password(userDAO.password())
                .build();

    }
}
