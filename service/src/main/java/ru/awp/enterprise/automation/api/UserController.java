package ru.awp.enterprise.automation.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.mapper.UserMapper;
import ru.awp.enterprise.automation.models.dto.UserDTO;
import ru.awp.enterprise.automation.models.request.UserChangePasswordRequest;
import ru.awp.enterprise.automation.models.request.UserChangeRequest;
import ru.awp.enterprise.automation.models.response.UserResponse;
import ru.awp.enterprise.automation.service.UserService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi{

    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserDTO> getCurrentUser() {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .flatMap(authentication -> userService.findByPhone(authentication.getPrincipal().toString()))
                .map(userMapper)
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));

    }

    @Override
    public Flux<UserResponse> getAllUser() {
        return userService.findAll();
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> updateUser(UUID id, UserChangeRequest userChangeRequest) {
        return userService.update(id, userChangeRequest)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> deleteUser(UUID id) {
        return userService.delete(id)
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

    @Override
    public Mono<ResponseEntity<HttpStatus>> changePassword(UserChangePasswordRequest userChangePasswordRequest) {
        return userService.changePassword(userChangePasswordRequest.id(), passwordEncoder.encode(userChangePasswordRequest.password()))
                .then(Mono.defer(() -> Mono.just(new ResponseEntity<>(HttpStatus.OK))));
    }

}
