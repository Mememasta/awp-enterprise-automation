package ru.awp.enterprise.automation.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;
import ru.awp.enterprise.automation.models.response.UserResponse;

import java.util.UUID;

public interface UserService {

    /**
     * Получить работника по id
     *
     * @param uuid {@link String} идентификатор работника
     * @return {@link Mono<UserDTO>} данные работника
     */
    Mono<UserDTO> findById(String uuid);

    /**
     * Получить работника по id
     *
     * @param uuid {@link UUID} идентификатор работника
     * @return {@link Mono<UserDTO>} данные работника
     */
    Mono<UserDAO> findById(UUID uuid);

    /**
     * Получить всех работников
     *
     * @return {@link Flux<UserResponse>} данные работников
     */
    Flux<UserResponse> findAll();

    Mono<UserDAO> findByPhone(String phoneNumber);

    Mono<UserDAO> save(UserDAO userDAO);
    Mono<Void> update(UUID uuid, UserChangeRequest request);
    Mono<Void> changePassword(UUID uuid, String password);
    Mono<Void> delete(UUID uuid);

}
