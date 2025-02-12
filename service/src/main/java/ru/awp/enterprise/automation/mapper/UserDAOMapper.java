package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;

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
                .authorities(signUpRequest.authorities())
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

    public UserDAO apply(UserDAO userDAO, String password) {
        return UserDAO.builder()
                .id(userDAO.id())
                .firstName(userDAO.firstName())
                .lastName(userDAO.lastName())
                .phoneNumber(userDAO.phoneNumber())
                .authorities(userDAO.authorities())
                .password(password)
                .build();

    }
}
