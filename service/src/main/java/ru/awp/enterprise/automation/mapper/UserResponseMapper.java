package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.response.UserResponse;

import java.util.function.Function;

@Component
public class UserResponseMapper implements Function<UserDAO, UserResponse> {

    @Override
    public UserResponse apply(UserDAO userDAO) {
        return UserResponse.builder()
                .id(userDAO.id().toString())
                .firstName(userDAO.firstName())
                .lastName(userDAO.lastName())
                .phoneNumber(userDAO.phoneNumber())
                .authorities(userDAO.authorities())
                .build();
    }
}
