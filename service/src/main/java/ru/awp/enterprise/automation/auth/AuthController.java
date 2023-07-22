package ru.awp.enterprise.automation.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.api.AuthApi;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;
import ru.awp.enterprise.automation.service.LoginService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final LoginService loginService;

    public Mono<ResponseEntity<LoginResponse>> login(LoginRequest request) {
        return loginService.login(request)
                .map(ResponseEntity::ok);
    }

    public Mono<ResponseEntity<Object>> signUp(SignUpRequest request) {
        return loginService.signUp(request)
                .map(it -> new ResponseEntity<>(it, HttpStatus.CREATED));
    }
}