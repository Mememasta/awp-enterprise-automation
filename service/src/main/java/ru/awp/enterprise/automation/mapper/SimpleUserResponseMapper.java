package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.response.SimpleUserResponse;

import java.util.Objects;
import java.util.function.Function;

@Component
public class SimpleUserResponseMapper implements Function<UserDAO, SimpleUserResponse> {
    @Override
    public SimpleUserResponse apply(UserDAO userDAO) {
        if (Objects.isNull(userDAO)) {
            return new SimpleUserResponse(null, null, null);
        }
        return SimpleUserResponse.builder()
                .id(String.valueOf(userDAO.id()))
                .firstName(userDAO.firstName())
                .lastName(userDAO.lastName())
                .build();
    }
}
