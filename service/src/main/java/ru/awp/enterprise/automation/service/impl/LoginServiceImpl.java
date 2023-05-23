package ru.awp.enterprise.automation.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.exception.ClientAlreadyExistsException;
import ru.awp.enterprise.automation.exception.ClientNotFoundException;
import ru.awp.enterprise.automation.jwt.JwtUtils;
import ru.awp.enterprise.automation.mapper.LoginResponseMapper;
import ru.awp.enterprise.automation.mapper.UserDAOMapper;
import ru.awp.enterprise.automation.models.dao.UserDAO;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;
import ru.awp.enterprise.automation.service.LoginService;
import ru.awp.enterprise.automation.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final ReactiveAuthenticationManager authenticationManager;

    private final LoginResponseMapper loginResponseMapper;

    private final JwtUtils jwtUtils;

    private final UserDAOMapper userDAOMapper;

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.phoneNumber(),
                        request.password(),
                        List.of(new SimpleGrantedAuthority("ROLE_USER"))
                )
        );
        return authentication
                .map(it -> loginResponseMapper.apply(jwtUtils.issueToken(String.valueOf(it.getPrincipal()), String.valueOf(it.getCredentials()))))
                .switchIfEmpty(Mono.error(ClientNotFoundException::new));

    }

    @Override
    public Mono<UserDAO> signUp(SignUpRequest signUpRequest) {
        var passwordEncode = passwordEncoder.encode(signUpRequest.password());

        final Mono<UserDAO> userDAO = userService.findByPhone(signUpRequest.phoneNumber())
                .flatMap(user -> Mono.error(new ClientAlreadyExistsException()))
                .switchIfEmpty(Mono.just(userDAOMapper.apply(signUpRequest, passwordEncode)))
                .ofType(UserDAO.class);

        return userDAO.flatMap(userService::save);

    }
}