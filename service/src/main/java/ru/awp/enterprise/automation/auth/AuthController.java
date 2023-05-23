package ru.awp.enterprise.automation.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.awp.enterprise.automation.models.request.LoginRequest;
import ru.awp.enterprise.automation.models.request.SignUpRequest;
import ru.awp.enterprise.automation.models.response.LoginResponse;
import ru.awp.enterprise.automation.service.LoginService;


@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {
    private final LoginService loginService;

    @PostMapping(value = "/auth")
    public Mono<ResponseEntity<LoginResponse>> login(@RequestBody @Validated LoginRequest request) {
        return loginService.login(request)
                .map(ResponseEntity::ok);
    }

    @PostMapping(value = "/signup")
    public Mono<ResponseEntity<Object>> signUp(@RequestBody @Validated SignUpRequest request) {
        return loginService.signUp(request)
                .map(it -> new ResponseEntity<>(it, HttpStatus.CREATED));
    }
}