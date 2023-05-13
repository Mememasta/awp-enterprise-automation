package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.mapper.UserMapper;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.repository.UserRepository;
import ru.awp.enterprise.automation.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
}
