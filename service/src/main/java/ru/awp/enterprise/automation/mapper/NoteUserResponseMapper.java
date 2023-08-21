package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.response.NoteUserResponse;

import java.util.Objects;
import java.util.function.Function;

@Component
public class NoteUserResponseMapper implements Function<UserDAO, NoteUserResponse> {
    @Override
    public NoteUserResponse apply(UserDAO userDAO) {
        if (Objects.isNull(userDAO)) {
            return new NoteUserResponse(null, null, null);
        }
        return NoteUserResponse.builder()
                .id(String.valueOf(userDAO.id()))
                .firstName(userDAO.firstName())
                .lastName(userDAO.lastName())
                .build();
    }
}
