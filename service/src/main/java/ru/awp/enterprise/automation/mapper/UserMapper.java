package ru.awp.enterprise.automation.mapper;

import org.springframework.stereotype.Component;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.dto.UserDTO;

import java.util.function.Function;

@Component
public class UserMapper implements Function<UserDAO, UserDTO> {

    @Override
    public UserDTO apply(UserDAO userDAO) {
        return UserDTO.builder()
                .id(userDAO.id().toString())
                .firstName(userDAO.firstName())
                .lastName(userDAO.lastName())
                .phoneNumber(userDAO.phoneNumber())
                .authorities(userDAO.authorities())
                .build();
    }
}
