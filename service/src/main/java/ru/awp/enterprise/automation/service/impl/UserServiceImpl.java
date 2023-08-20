package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.mapper.UserDAOMapper;
import ru.awp.enterprise.automation.mapper.UserMapper;
import ru.awp.enterprise.automation.mapper.UserResponseMapper;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;
import ru.awp.enterprise.automation.models.response.UserResponse;
import ru.awp.enterprise.automation.repository.UserRepository;
import ru.awp.enterprise.automation.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserResponseMapper userResponseMapper;
    private final UserDAOMapper userDAOMapper;

    @Override
    public Mono<UserDTO> findById(String id) {
        try {
            var uuid = UUID.fromString(id);
            return userRepository.findById(uuid)
                    .map(userMapper)
                    .switchIfEmpty(Mono.empty());
        } catch (IllegalArgumentException e) {
            return Mono.error(new RuntimeException(e.getMessage(), e));
        }
    }

    @Override
    public Flux<UserResponse> findAll() {
        return userRepository.findAll()
                .map(userResponseMapper)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<UserDAO> findByPhone(String phoneNumber) {
        return userRepository.findFirstByPhoneNumber(phoneNumber)
                    .switchIfEmpty(Mono.empty());
    }

    @Override
    @Transactional
    public Mono<UserDAO> save(UserDAO userDAO) {
        return userRepository.save(userDAO)
                .onErrorResume(e -> Mono.error(new ClientNotFoundException()));
    }

    @Override
    @Transactional
    public Mono<Void> update(UUID uuid, UserChangeRequest request) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new))
                .flatMap(user -> userRepository.save(userDAOMapper.apply(uuid, request, user)))
                .flatMap(it -> Mono.empty());
    }

    @Override
    public Mono<Void> delete(UUID uuid) {
        return userRepository.findById(uuid)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new))
                .flatMap(user -> userRepository.deleteById(uuid));
    }
}
