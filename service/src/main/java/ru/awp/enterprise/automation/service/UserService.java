package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.dto.UserDTO;

public interface UserService {

    /**
     * Получить работника по id
     *
     * @param uuid {@link String} идентификатор работника
     * @return {@link Mono<UserDTO>} данные работника
     */
    Mono<UserDTO> findById(String uuid);

    /**
     * Получить всех работников
     *
     * @return {@link Flux<UserDTO>} данные работников
     */
    Flux<UserDTO> findAll();

    Mono<UserDAO> findByPhone(String phoneNumber);

    Mono<UserDAO> save(UserDAO userDAO);

}
