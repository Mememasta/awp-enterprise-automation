package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.mapper.UserMapper;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi{

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public Mono<UserDTO> getCurrentUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> userService.findByPhone(authentication.getPrincipal().toString()))
                .map(userMapper)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));

    }
}
