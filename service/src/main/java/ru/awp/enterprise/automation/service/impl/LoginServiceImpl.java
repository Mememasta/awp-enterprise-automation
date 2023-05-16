package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.jwt.JwtUtils;
import ru.awp.enterprise.automation.mapper.LoginResponseMapper;
import ru.awp.enterprise.automation.mapper.UserMapper;
import ru.awp.enterprise.automation.service.LoginService;
import ru.awp.enterprise.automation.service.UserService;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final LoginResponseMapper loginResponseMapper;

    private final JwtUtils jwtUtils;

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        var userDTO = userService
                .findByPhone(request.phoneNumber())
                .filter(user -> passwordEncoder.matches(request.password(), user.password()))
                .map(userMapper);

        return userDTO
                .map(it -> loginResponseMapper.apply(it, jwtUtils.issueToken(String.valueOf(it.id()))))
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));
    }
}